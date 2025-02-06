package com.restaurant.restaurant.user;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {
    private UUID id;
    private String email;
    private String userName;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
