package com.fayupable.expensetracker.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EntityListeners;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(LoginInfoListener.class)
public class LoginInfo {
    private LocalDateTime loginTime;
    private LocalDateTime logoutTime;
    private LocalDateTime lastLoginTime;
}
