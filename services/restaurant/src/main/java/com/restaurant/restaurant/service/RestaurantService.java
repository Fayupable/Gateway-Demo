package com.restaurant.restaurant.service;

import com.restaurant.restaurant.dto.RestaurantDto;
import com.restaurant.restaurant.entity.Restaurant;
import com.restaurant.restaurant.mapper.RestaurantMapper;
import com.restaurant.restaurant.repository.IRestaurantRepository;
import com.restaurant.restaurant.request.AddRestaurantRequest;
import com.restaurant.restaurant.user.UserDto;
import com.restaurant.restaurant.user.client.AuthClient;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantService implements IRestaurantService {
    private final IRestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;
    private final AuthClient authClient;


    @Override
    public RestaurantDto addRestaurant(AddRestaurantRequest request, HttpServletRequest httpServletRequest) {
        return Optional.of(request)
                .map(req -> createRestaurantFromRequest(req, httpServletRequest))
                .map(restaurantRepository::save)
                .map(restaurantMapper::fromRestaurant)
                .orElseThrow(() -> new RuntimeException("Restaurant creation failed!"));
    }

    private Restaurant createRestaurantFromRequest(AddRestaurantRequest addRestaurantRequest, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        UserDto currentUser = authClient.getCurrentUser(token);
        Restaurant restaurant = new Restaurant();
        restaurant.setId(UUID.randomUUID());
        restaurant.setName(addRestaurantRequest.getName());
        restaurant.setAddress(addRestaurantRequest.getAddress());
        restaurant.setPhone(addRestaurantRequest.getPhone());
        restaurant.setOwnerId(currentUser.getId());
        return restaurant;
    }

    @Override
    public List<RestaurantDto> getRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream()
                .map(restaurantMapper::fromRestaurant)
                .collect(Collectors.toList());
    }

}
