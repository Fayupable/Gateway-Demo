package com.fayupable.expensetracker.service;

import com.fayupable.expensetracker.dto.UserDto;
import com.fayupable.expensetracker.entity.UserCredential;
import com.fayupable.expensetracker.request.LoginRequest;
import com.fayupable.expensetracker.request.user.AddUserRequest;
import com.fayupable.expensetracker.request.user.UpdateUserRequest;
import com.fayupable.expensetracker.response.AuthResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface IAuthService {
    ResponseEntity<AuthResponse> login(LoginRequest request);

    boolean validateToken(String token);

    ResponseEntity<AuthResponse> logout(HttpServletRequest request);

    UserDto getCurrentUserId(String id);

    UserDto addUser(AddUserRequest request);

    UserCredential updateUser(UpdateUserRequest request);

    ResponseEntity<AuthResponse> validateTokenFromHeader(HttpServletRequest request);
}
