package com.etienne.client.store.service;

import com.etienne.client.store.model.auth.OpticsUser;
import com.etienne.client.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MongoUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        OpticsUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Felhasználó nem található: " + username));
        return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
//        return new User("foo", "foo", new ArrayList<>());
    }

}
