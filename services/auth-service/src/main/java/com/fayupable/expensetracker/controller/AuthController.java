package com.fayupable.expensetracker.controller;


import com.fayupable.expensetracker.dto.UserDto;
import com.fayupable.expensetracker.request.LoginRequest;
import com.fayupable.expensetracker.request.user.AddUserRequest;
import com.fayupable.expensetracker.response.AuthResponse;
import com.fayupable.expensetracker.service.IAuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/logout")
    public ResponseEntity<AuthResponse> logout(HttpServletRequest request) {
        return authService.logout(request);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody AddUserRequest request) {
        return ResponseEntity.ok(authService.addUser(request));
    }

    @PostMapping("/validate")
    public ResponseEntity<AuthResponse> validate(String request) {
        return ResponseEntity.ok(new AuthResponse("Token is valid", authService.validateToken(request)));
    }

    @GetMapping("/current-user")
    public ResponseEntity<UserDto> getCurrentUser(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            UserDto user = authService.getCurrentUserId(token);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
