package com.fayupable.expensetracker.repository;

import com.fayupable.expensetracker.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IUserCredentialRepository extends JpaRepository<UserCredential, String> {
    UserCredential findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUserName(String userName);

    Optional<UserCredential> findByUserId(UUID userId);




}
