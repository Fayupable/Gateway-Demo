package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.request.AddRestaurantRequest;
import com.restaurant.restaurant.response.RestaurantResponse;
import com.restaurant.restaurant.service.IRestaurantService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {
    private final IRestaurantService restaurantService;

    @GetMapping("/all")
    public ResponseEntity<RestaurantResponse> getRestaurants() {
        return ResponseEntity.ok(new RestaurantResponse("Restaurants retrieved successfully", restaurantService.getRestaurants()));
    }

    @PostMapping("/add")
    public ResponseEntity<RestaurantResponse> addRestaurant(@RequestBody AddRestaurantRequest addRestaurantRequest, HttpServletRequest request) {
        return ResponseEntity.ok(new RestaurantResponse("Restaurant added successfully", restaurantService.addRestaurant(addRestaurantRequest, request)));
    }
}
