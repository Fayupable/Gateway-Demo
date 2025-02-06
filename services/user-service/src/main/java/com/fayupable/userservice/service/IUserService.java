package com.fayupable.userservice.service;

import com.fayupable.userservice.dto.UserDto;
import com.fayupable.userservice.request.AddUserRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface IUserService {
    UserDto addUser(AddUserRequest request, HttpServletRequest httpServletRequest);

    List<UserDto> getUsers();
}
