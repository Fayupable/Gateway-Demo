package com.fayupable.expensetracker.entity;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class LoginInfoListener {
    @PrePersist
    public void prePersist(LoginInfo loginInfo) {
        loginInfo.setLoginTime(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate(LoginInfo loginInfo) {
        loginInfo.setLastLoginTime(LocalDateTime.now());
    }

}
