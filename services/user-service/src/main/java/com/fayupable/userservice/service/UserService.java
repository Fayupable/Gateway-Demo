package com.fayupable.userservice.service;

import com.fayupable.userservice.dto.UserDto;
import com.fayupable.userservice.entity.User;
import com.fayupable.userservice.mapper.UserMapper;
import com.fayupable.userservice.repository.IUserRepository;
import com.fayupable.userservice.request.AddUserRequest;
import com.fayupable.userservice.user.client.AuthClient;
import com.fayupable.userservice.user.dto.AuthDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final AuthClient authClient;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDto addUser(AddUserRequest request, HttpServletRequest httpServletRequest) {
        return Optional.of(request)
                .map(req -> createUser(req, httpServletRequest))
                .map(userRepository::save)
                .map(userMapper::fromUser)
                .orElseThrow(() -> new RuntimeException("User creation failed!"));
    }

    private User createUser(AddUserRequest addUserRequest, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        AuthDto currentUser = authClient.getCurrentUser(token);
        User user = new User();
        user.setId(currentUser.getId());
        user.setUserSettings(addUserRequest.getUserSettings());
        user.setAddress(addUserRequest.getAddress());
        return user;
    }


    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::fromUser)
                .collect(Collectors.toList());
    }
}
