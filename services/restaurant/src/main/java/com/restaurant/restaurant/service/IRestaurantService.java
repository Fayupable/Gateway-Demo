package com.restaurant.restaurant.service;

import com.restaurant.restaurant.dto.RestaurantDto;
import com.restaurant.restaurant.request.AddRestaurantRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface IRestaurantService {
    RestaurantDto addRestaurant(AddRestaurantRequest addRestaurantRequest, HttpServletRequest request);

    List<RestaurantDto> getRestaurants();
}
