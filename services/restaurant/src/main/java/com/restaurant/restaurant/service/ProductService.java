package com.restaurant.restaurant.service;

import com.restaurant.restaurant.dto.ProductDto;
import com.restaurant.restaurant.dto.ProductPurchaseDto;
import com.restaurant.restaurant.entity.Product;
import com.restaurant.restaurant.exception.ProductPurchaseException;
import com.restaurant.restaurant.mapper.ProductMapper;
import com.restaurant.restaurant.repository.IProductRepository;
import com.restaurant.restaurant.repository.IRestaurantRepository;
import com.restaurant.restaurant.request.AddProductPurchaseRequest;
import com.restaurant.restaurant.request.AddProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final IProductRepository productRepository;
    private final ProductMapper productMapper;
    private final IRestaurantRepository restaurantRepository;


    @Override
    public ProductDto addProduct(AddProductRequest addProductRequest) {
        return Optional.of(addProductRequest)
                .map(this::createProductFromRequest)
                .map(productRepository::save)
                .map(productMapper::fromProduct)
                .orElseThrow(() -> new RuntimeException("Product creation failed!"));

    }

    private Product createProductFromRequest(AddProductRequest addProductRequest) {
        if (!isRestaurantExists(addProductRequest.getRestaurantId())) {
            throw new RuntimeException("Restaurant not found");
        }

        if (!checkProductPrice(addProductRequest.getPrice())) {
            throw new RuntimeException("Invalid product price");
        }
        Product product = new Product();
        product.setId(UUID.randomUUID());
        product.setName(addProductRequest.getName());
        product.setDescription(addProductRequest.getDescription());
        product.setPrice(addProductRequest.getPrice());
        product.setRestaurant(restaurantRepository.findById(addProductRequest.getRestaurantId()).orElseThrow(() -> new RuntimeException("Restaurant not found")));
        return product;
    }


    private boolean isRestaurantExists(UUID restaurantId) {
        return restaurantRepository.existsById(restaurantId);
    }

    private boolean checkProductPrice(double price) {
        return price > 0;
    }

    @Transactional
    @Override
    public List<ProductPurchaseDto> purchaseProducts(List<AddProductPurchaseRequest> addProductPurchaseRequests) {
        for (AddProductPurchaseRequest request : addProductPurchaseRequests) {
            if (!isProductExists(request.getProductId())) {
                throw new ProductPurchaseException("Product not found: " + request.getProductId());
            }
        }

        return addProductPurchaseRequests.stream()
                .map(this::processPurchase)
                .toList();
    }

    private ProductPurchaseDto processPurchase(AddProductPurchaseRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ProductPurchaseException("Product not found: " + request.getProductId()));
        return productMapper.toProductPurchaseDto(product, request.getQuantity());
    }

    private boolean isProductExists(UUID productId) {
        return productRepository.existsById(productId);
    }

    @Override
    public List<ProductDto> getProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::fromProduct)
                .toList();
    }

    @Override
    public ProductDto getProductById(UUID id) {
        return productRepository.findById(id)
                .map(productMapper::fromProduct)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
