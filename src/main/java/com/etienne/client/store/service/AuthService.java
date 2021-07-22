package com.etienne.client.store.service;

import com.etienne.client.store.model.auth.AuthenticationRequest;
import com.etienne.client.store.model.auth.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public ResponseEntity<AuthenticationResponse> generateToken(AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()));
            return ok(new AuthenticationResponse(jwtService.generateToken(authenticationRequest.getUsername())));
        }catch (AuthenticationException e) {
            throw new Exception("helytelen felhasználónév vagy jelszó");
        }
    }

}

