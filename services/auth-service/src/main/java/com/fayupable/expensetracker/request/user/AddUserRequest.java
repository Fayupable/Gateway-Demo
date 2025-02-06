package com.fayupable.expensetracker.request.user;


import com.fayupable.expensetracker.Enum.Role;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class AddUserRequest {


    private String email;

    private String userName;

    private String firstName;

    private String lastName;

    private String password;

    private Date dateOfBirth;

    private String phoneNumber;

    private Set<Role> roles;

}
