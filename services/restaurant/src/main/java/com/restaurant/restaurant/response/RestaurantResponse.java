package com.restaurant.restaurant.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestaurantResponse {
    private String message;
    private Object data;
}
