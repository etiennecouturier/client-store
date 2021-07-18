package com.etienne.client.store.controller;

import com.etienne.client.store.model.auth.AuthenticationRequest;
import com.etienne.client.store.model.auth.AuthenticationResponse;
import com.etienne.client.store.service.JwtService;
import com.etienne.client.store.service.MongoUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final MongoUserDetailsService mongoUserDetailsService;
    private final JwtService jwtService;

    @PostMapping(path = "/auth")
    public ResponseEntity<AuthenticationResponse> filterClients(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Bad credentials");
        }
        UserDetails userDetails = mongoUserDetailsService.loadUserByUsername(authenticationRequest.getUserName());
        String jwt = jwtService.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
