package com.fayupable.userservice.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class UserSettings {
    private boolean notificationsEnabled;
    private String preferredLanguage;
    private String themePreference;
}