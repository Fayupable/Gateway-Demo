package com.restaurant.restaurant.service;

import com.restaurant.restaurant.dto.ProductDto;
import com.restaurant.restaurant.dto.ProductPurchaseDto;
import com.restaurant.restaurant.request.AddProductPurchaseRequest;
import com.restaurant.restaurant.request.AddProductRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface IProductService {

    ProductDto addProduct(AddProductRequest addProductRequest);

    @Transactional
    List<ProductPurchaseDto> purchaseProducts(List<AddProductPurchaseRequest> addProductPurchaseRequests);

    List<ProductDto> getProducts();

    ProductDto getProductById(UUID id);
}
