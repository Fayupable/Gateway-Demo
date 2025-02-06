package com.fayupable.userservice.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {
    private AddressDto address;
    private UserSettingsDto userSettings;

}
