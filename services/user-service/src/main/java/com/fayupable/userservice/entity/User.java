package com.fayupable.userservice.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "users")
public class User {
    @Id
    private UUID id;

    @Embedded
    private Address address;

    @Embedded
    private UserSettings userSettings;

}
