package com.fayupable.expensetracker.mapper;

import com.fayupable.expensetracker.dto.UserDto;
import com.fayupable.expensetracker.entity.UserCredential;
import com.fayupable.expensetracker.request.user.AddUserRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserCredential toUser(AddUserRequest request) {
        return UserCredential.builder()
                .email(request.getEmail())
                .userName(request.getUserName())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(request.getPassword())
                .dateOfBirth(request.getDateOfBirth())
                .phoneNumber(request.getPhoneNumber())
                .roles(request.getRoles())
                .build();

    }

    public UserDto fromUser(UserCredential userCredential) {
        UserDto userDto = new UserDto();
        userDto.setEmail(userCredential.getEmail());
        userDto.setPassword(userCredential.getPassword());
        userDto.setUserName(userCredential.getUserName());
        userDto.setFirstName(userCredential.getFirstName());
        userDto.setLastName(userCredential.getLastName());
        userDto.setDateOfBirth(userCredential.getDateOfBirth());
        userDto.setPhoneNumber(userCredential.getPhoneNumber());
        userDto.setRoles(userCredential.getRoles());
        return userDto;
    }


}
