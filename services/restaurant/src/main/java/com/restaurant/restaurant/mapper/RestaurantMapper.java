package com.restaurant.restaurant.mapper;

import com.restaurant.restaurant.dto.RestaurantDto;
import com.restaurant.restaurant.entity.Restaurant;
import com.restaurant.restaurant.request.AddRestaurantRequest;
import org.springframework.stereotype.Service;

@Service
public class RestaurantMapper {
    public Restaurant toRestaurant(AddRestaurantRequest request) {
        return Restaurant.builder()
                .name(request.getName())
                .address(request.getAddress())
                .phone(request.getPhone())
                .ownerId(request.getOwnerId())
                .build();
    }

    public RestaurantDto fromRestaurant(Restaurant restaurant) {
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setName(restaurant.getName());
        restaurantDto.setAddress(restaurant.getAddress());
        restaurantDto.setPhoneNumber(restaurant.getPhone());
        restaurantDto.setOwnerId(restaurant.getOwnerId());
        return restaurantDto;
    }

}
