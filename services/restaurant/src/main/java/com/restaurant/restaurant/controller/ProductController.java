package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.request.AddProductPurchaseRequest;
import com.restaurant.restaurant.request.AddProductRequest;
import com.restaurant.restaurant.response.RestaurantResponse;
import com.restaurant.restaurant.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/restaurant/product")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService productService;

    @GetMapping("/all")
    public ResponseEntity<RestaurantResponse> getProducts() {
        return ResponseEntity.ok(new RestaurantResponse("Products retrieved successfully", productService.getProducts()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponse> getProductById(@PathVariable UUID id) {
        return ResponseEntity.ok(new RestaurantResponse("Product retrieved successfully", productService.getProductById(id)));

    }

    @PostMapping("/add")
    public ResponseEntity<RestaurantResponse> addProduct(@RequestBody AddProductRequest addProductRequest) {
        return ResponseEntity.ok(new RestaurantResponse("Product added successfully", productService.addProduct(addProductRequest)));
    }

    @PostMapping("/purchase")
    public ResponseEntity<RestaurantResponse> purchaseProduct(@RequestBody List<AddProductPurchaseRequest> addProductPurchaseRequest) {
        return ResponseEntity.ok(new RestaurantResponse("Product purchased successfully", productService.purchaseProducts(addProductPurchaseRequest)));
    }


}
