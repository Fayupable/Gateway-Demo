package com.fayupable.userservice.mapper;

import com.fayupable.userservice.dto.AddressDto;
import com.fayupable.userservice.dto.UserDto;
import com.fayupable.userservice.dto.UserSettingsDto;
import com.fayupable.userservice.entity.User;
import com.fayupable.userservice.request.AddUserRequest;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public User toUser(AddUserRequest request) {
        if (request == null) {
            return null;
        }
        return User.builder()
                .address(request.getAddress())
                .userSettings(request.getUserSettings())
                .build();
    }

    public UserDto fromUser(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        AddressDto addressDto = new AddressDto();
        UserSettingsDto userSettingsDto = new UserSettingsDto();
        addressDto.setStreet(user.getAddress().getStreet());
        addressDto.setHouseNumber(user.getAddress().getHouseNumber());
        addressDto.setZipCode(user.getAddress().getZipCode());
        userSettingsDto.setTheme(user.getUserSettings().getThemePreference());
        userSettingsDto.setNotificationsEnabled(user.getUserSettings().isNotificationsEnabled());
        userSettingsDto.setPreferredLanguage(user.getUserSettings().getPreferredLanguage());
        userDto.setAddress(addressDto);
        userDto.setUserSettings(userSettingsDto);
        return userDto;
    }
}
