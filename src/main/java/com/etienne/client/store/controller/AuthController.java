package com.etienne.client.store.controller;

import com.etienne.client.store.model.auth.AuthenticationRequest;
import com.etienne.client.store.model.auth.AuthenticationResponse;
import com.etienne.client.store.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    @PostMapping(path = "/generate-token")
    public ResponseEntity<AuthenticationResponse> generateToken(@RequestBody AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()));
        return ok(new AuthenticationResponse(jwtService.generateToken(authenticationRequest.getUsername())));
    }

}
