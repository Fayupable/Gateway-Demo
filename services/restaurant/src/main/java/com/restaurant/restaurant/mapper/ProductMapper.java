package com.restaurant.restaurant.mapper;

import com.restaurant.restaurant.dto.ProductDto;
import com.restaurant.restaurant.dto.ProductPurchaseDto;
import com.restaurant.restaurant.entity.Product;
import com.restaurant.restaurant.request.AddProductRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toProduct(AddProductRequest request) {
        return Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();
    }

    public ProductDto fromProduct(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setRestaurantId(product.getRestaurant().getId());
        return productDto;
    }

    public ProductPurchaseDto toProductPurchaseDto(Product product, double quantity) {
        ProductPurchaseDto productPurchaseDto = new ProductPurchaseDto();
        productPurchaseDto.setProductId(product.getId());
        productPurchaseDto.setName(product.getName());
        productPurchaseDto.setDescription(product.getDescription());
        productPurchaseDto.setPrice(product.getPrice());
        productPurchaseDto.setQuantity(quantity);
        return productPurchaseDto;
    }
}
