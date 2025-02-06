package com.fayupable.expensetracker.entity;

import com.fayupable.expensetracker.Enum.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_credential")

public class UserCredential {
    @Id
    @Column(name = "User_id", updatable = false, nullable = false)
    private UUID userId;
    private String email;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;
    private String phoneNumber;


    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Embedded
    private LoginInfo loginInfo;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();


}
