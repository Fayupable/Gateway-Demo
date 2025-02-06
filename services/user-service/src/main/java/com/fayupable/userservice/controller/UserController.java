package com.fayupable.userservice.controller;

import com.fayupable.userservice.request.AddUserRequest;
import com.fayupable.userservice.response.UserResponse;
import com.fayupable.userservice.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    @GetMapping("/all")
    public ResponseEntity<UserResponse> getUsers() {
        return ResponseEntity.ok(new UserResponse("Users retrieved successfully", userService.getUsers()));
    }

    @PostMapping("/add")
    public ResponseEntity<UserResponse> addUser(@RequestBody AddUserRequest addUserRequest, HttpServletRequest request) {
        return ResponseEntity.ok(new UserResponse("User added successfully", userService.addUser(addUserRequest, request)));
    }
}
