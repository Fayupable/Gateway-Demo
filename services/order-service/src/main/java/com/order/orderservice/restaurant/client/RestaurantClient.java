package com.order.orderservice.restaurant.client;

import com.order.orderservice.exception.BusinessException;
import com.order.orderservice.restaurant.dto.PurchaseDto;
import com.order.orderservice.restaurant.request.AddPurchaseRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestaurantClient {

    @Value("${application.config.restaurant-url}")
    private String restaurantServiceUrl;
    private final RestTemplate restTemplate;

    public List<PurchaseDto> purchaseProduct(List<AddPurchaseRequest> addPurchaseRequests) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<List<AddPurchaseRequest>> requestEntity = new HttpEntity<>(addPurchaseRequests, headers);
        log.info("Sending purchase request to {}", restaurantServiceUrl + "/product/purchase");

        try {
            ResponseEntity<String> rawResponse = restTemplate.exchange(
                    restaurantServiceUrl + "/product/purchase",
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );

            log.info("Received raw response: {}", rawResponse.getBody());

            if (rawResponse.getStatusCode().isError()) {
                log.error("An error occurred while processing the products purchase: {}", rawResponse.getStatusCode());
                throw new BusinessException("An error occurred while processing the products purchase: " + rawResponse.getStatusCode());
            }

            if (rawResponse.getBody().trim().startsWith("[")) {
                ResponseEntity<List<PurchaseDto>> responseEntity = restTemplate.exchange(
                        restaurantServiceUrl + "/product/purchase",
                        HttpMethod.POST,
                        requestEntity,
                        new ParameterizedTypeReference<>() {}
                );
                return responseEntity.getBody();
            } else {
                ResponseEntity<PurchaseDto> responseEntity = restTemplate.exchange(
                        restaurantServiceUrl + "/product/purchase",
                        HttpMethod.POST,
                        requestEntity,
                        PurchaseDto.class
                );
                List<PurchaseDto> purchases = new ArrayList<>();
                purchases.add(responseEntity.getBody());
                return purchases;
            }
        } catch (Exception e) {
            log.error("Error during product purchase: {}", e.getMessage(), e);
            throw new BusinessException("An unexpected error occurred while purchasing products.");
        }
    }
}