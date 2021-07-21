package com.etienne.client.store.service;

import com.etienne.client.store.model.auth.OpticsUser;
import com.etienne.client.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public OpticsUser saveUser(OpticsUser user) {
        user.setPassword(
                passwordEncoder.encode(user.getPassword()));
        return userRepository.insert(user);
    }

}

