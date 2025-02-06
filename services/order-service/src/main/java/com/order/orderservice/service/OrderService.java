package com.order.orderservice.service;

import com.order.orderservice.dto.OrderDto;
import com.order.orderservice.exception.BusinessException;
import com.order.orderservice.kafka.OrderConfirmation;
import com.order.orderservice.kafka.OrderProducer;
import com.order.orderservice.mapper.OrderMapper;
import com.order.orderservice.payment.PaymentClient;
import com.order.orderservice.payment.request.PaymentRequest;
import com.order.orderservice.repository.IOrderRepository;
import com.order.orderservice.request.AddOrderLineRequest;
import com.order.orderservice.request.AddOrderRequest;
import com.order.orderservice.restaurant.client.RestaurantClient;
import com.order.orderservice.restaurant.request.AddPurchaseRequest;
import com.order.orderservice.user.client.IUserClient;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final IOrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final IUserClient userClient;
    private final RestaurantClient restaurantClient;
    private final IOrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    @Override
    public UUID createOrder(AddOrderRequest addOrderRequest, HttpServletRequest request) {
        // Check the user
        String token = request.getHeader("Authorization");
        UUID userId = getUserId(token);

        var user = this.userClient.getCurrentUser(token);

        //purchase the product
//        purchaseProduct(addOrderRequest);

        var purchasedProducts = this.restaurantClient.purchaseProduct(addOrderRequest.getProducts());


        //persist the order
        var order = this.orderMapper.toOrder(addOrderRequest);
        order.setId(UUID.randomUUID());
        order.setUserId(userId);
        order = this.orderRepository.save(order);

        //persist the order lines
        for (AddPurchaseRequest addPurchaseRequest : addOrderRequest.getProducts()) {
            orderLineService.saveOrderLine(
                    new AddOrderLineRequest(
                            null,
                            order.getId(),
                            addPurchaseRequest.getProductId(),
                            addPurchaseRequest.getQuantity()
                    )

            );
        }

        //start payment process
        var paymentRequest = new PaymentRequest(
                addOrderRequest.getAmount(),
                addOrderRequest.getPaymentMethod(),
                order.getId(),
                order.getReference(),
                user
        );
        paymentClient.requestOrderPayment(paymentRequest);

        //send the order confirmation email (notification (kafka))
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        addOrderRequest.getReference(),
                        addOrderRequest.getAmount(),
                        addOrderRequest.getPaymentMethod(),
                        user,
                        purchasedProducts
                )
        );
        return order.getId();
    }


    //check the user
    private UUID getUserId(String token) {
        var user = this.userClient.getCurrentUser(token);
        if (user != null) {
            return user.getId();
        } else {
            throw new BusinessException("User not found with the given token");
        }
    }

    //purchase the product
    private void purchaseProduct(AddOrderRequest addOrderRequest) {
        this.restaurantClient.purchaseProduct(addOrderRequest.getProducts());
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toOrderDto)
                .toList();
    }

    @Override
    public OrderDto getOrderById(UUID orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::toOrderDto)
                .orElseThrow(() -> new BusinessException("Order not found with the given id"));
    }
}
