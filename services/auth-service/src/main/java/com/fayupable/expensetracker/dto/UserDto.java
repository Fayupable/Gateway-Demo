package com.fayupable.expensetracker.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fayupable.expensetracker.Enum.Role;
import lombok.Data;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
public class UserDto {
    private UUID id;
    private String email;
    @JsonIgnore
    private String password;
    private String userName;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String phoneNumber;
    private Set<Role> roles;

}
