package com.etienne.client.store.controller;

import com.etienne.client.store.model.auth.AuthenticationRequest;
import com.etienne.client.store.model.auth.AuthenticationResponse;
import com.etienne.client.store.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/generate-token")
    public ResponseEntity<AuthenticationResponse> generateToken(
            @RequestBody AuthenticationRequest authenticationRequest) {
        return authService.generateToken(authenticationRequest);
    }

}
