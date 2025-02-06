package com.fayupable.userservice.dto;

import lombok.Data;

@Data
public class UserSettingsDto {
    private boolean notificationsEnabled;
    private String preferredLanguage;
    private String theme;
}
