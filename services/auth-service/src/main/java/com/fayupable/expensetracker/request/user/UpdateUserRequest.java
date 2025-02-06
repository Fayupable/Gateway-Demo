package com.fayupable.expensetracker.request.user;

import com.fayupable.expensetracker.Enum.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UpdateUserRequest {
    private String email;
    private String userName;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String dateOfBirth;
    private String password;
    private Set<Role> roles;

}
