package com.fayupable.expensetracker.service;

import com.fayupable.expensetracker.Enum.Role;
import com.fayupable.expensetracker.dto.UserDto;
import com.fayupable.expensetracker.entity.UserCredential;
import com.fayupable.expensetracker.exception.AlreadyExistsException;
import com.fayupable.expensetracker.mapper.UserMapper;
import com.fayupable.expensetracker.repository.IUserCredentialRepository;
import com.fayupable.expensetracker.request.LoginRequest;
import com.fayupable.expensetracker.request.user.AddUserRequest;
import com.fayupable.expensetracker.request.user.UpdateUserRequest;
import com.fayupable.expensetracker.response.AuthResponse;
import com.fayupable.expensetracker.response.JwtResponse;
import com.fayupable.expensetracker.security.jwt.JwtUtils;
import com.fayupable.expensetracker.security.user.UserDetails;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final IUserCredentialRepository userCredentialRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<AuthResponse> login(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtUtils.generateTokenForUser(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            return ResponseEntity.ok(new AuthResponse("Login success", new JwtResponse(userDetails.getId(), jwt)));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse("Invalid credentials", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new AuthResponse("Login failed: " + e.getMessage(), null));
        }
    }

    @Override
    public boolean validateToken(String token) {
        try {
            if (jwtUtils.isTokenBlacklisted(token)) {
                return false;
            }
            return jwtUtils.validateToken(token);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ResponseEntity<AuthResponse> validateTokenFromHeader(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            boolean isValid = validateToken(token);
            return ResponseEntity.ok(new AuthResponse("Token is valid", isValid));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new AuthResponse("Invalid token", false));
        }
    }


    @Override
    public ResponseEntity<AuthResponse> logout(HttpServletRequest request) {
        try {
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);

                jwtUtils.blacklistToken(token);

                SecurityContextHolder.clearContext();

                return ResponseEntity.ok(new AuthResponse("Logout successful", null));
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse("No token provided", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new AuthResponse("Logout failed: " + e.getMessage(), null));
        }
    }

    public UserDto getCurrentUserId(String token) {
        String userId = jwtUtils.getUserIdFromToken(token);
        System.out.println("Extracted User ID from token: " + userId);

        UUID uuid;
        try {
            uuid = UUID.fromString(userId);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid User ID format from token: " + userId);
        }

        UserCredential user = userCredentialRepository.findByUserId(uuid)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + uuid));

        // ✅ BURADA `id` KULLANILDIĞINDAN EMİN OL!
        UserDto userDto = userMapper.fromUser(user);
        userDto.setId(user.getUserId());  // **DTO içine ID'yi ekliyoruz!**

        return userDto;
    }

    @Override
    public UserDto addUser(AddUserRequest request) {
        return Optional.of(request)
                .map(this::createUser)
                .map(user -> {
                    checkEmailExists(user);
                    checkUserNameExists(user);
                    assignRoleToUser(user, request);
                    user.setUserId(UUID.randomUUID());
                    return userCredentialRepository.save(user);
                })
                .map(userMapper::fromUser)
                .orElseThrow(() -> new RuntimeException("User creation failed!"));
    }

    private UserCredential createUser(AddUserRequest request) {
        UserCredential user = new UserCredential();
        user.setEmail(request.getEmail());
        user.setUserName(request.getUserName());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setDateOfBirth(request.getDateOfBirth());
        user.setPhoneNumber(request.getPhoneNumber());
        return user;
    }

    private void checkEmailExists(UserCredential user) {
        if (userCredentialRepository.existsByEmail(user.getEmail())) {
            throw new AlreadyExistsException("Email already exists");
        }
    }

    private void checkUserNameExists(UserCredential user) {
        if (userCredentialRepository.existsByUserName(user.getUserName())) {
            throw new AlreadyExistsException("Username already exists");
        }
    }

    private void assignRoleToUser(UserCredential user, AddUserRequest request) {
        if (request.getRoles() == null || request.getRoles().isEmpty()) {
            user.setRoles(Set.of(Role.ROLE_USER));
        } else {
            Set<Role> roles = request.getRoles().stream()
                    .map(role -> Role.valueOf(role.toString()))
                    .collect(Collectors.toSet());
            user.setRoles(roles);
        }
    }


    @Override
    public UserCredential updateUser(UpdateUserRequest request) {
        return null;
    }
}