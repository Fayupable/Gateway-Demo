package com.fayupable.expensetracker.security.user;

import com.fayupable.expensetracker.entity.UserCredential;
import com.fayupable.expensetracker.repository.IUserCredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final IUserCredentialRepository userCredentialRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserCredential userCredential = Optional.ofNullable(userCredentialRepository.findByEmail(email))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return com.fayupable.expensetracker.security.user.UserDetails.buildUserDetails(userCredential);
    }
}
