package com.fayupable.userservice.request;

import com.fayupable.userservice.entity.Address;
import com.fayupable.userservice.entity.UserSettings;
import lombok.Data;

@Data
public class AddUserRequest {
    private Address address;
    private UserSettings userSettings;
}
