package com.restaurant.restaurant.user.client;

import com.restaurant.restaurant.user.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-service", url = "${application.config.auth-url}")
public interface AuthClient {
    @GetMapping("/current-user")
    UserDto getCurrentUser(@RequestHeader("Authorization") String token);

}
