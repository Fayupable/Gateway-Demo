package com.order.orderservice.user.client;

import com.order.orderservice.user.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-service", url = "${application.config.auth-url}")
public interface IUserClient {
    @GetMapping("/current-user")
    UserDto getCurrentUser(@RequestHeader("Authorization") String token);
}
