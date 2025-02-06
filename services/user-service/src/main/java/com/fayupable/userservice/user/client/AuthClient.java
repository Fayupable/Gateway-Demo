package com.fayupable.userservice.user.client;

import com.fayupable.userservice.user.dto.AuthDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-service", url = "${application.config.auth-url}")
public interface AuthClient {
    @GetMapping("/current-user")
    AuthDto getCurrentUser(@RequestHeader("Authorization") String token);

}