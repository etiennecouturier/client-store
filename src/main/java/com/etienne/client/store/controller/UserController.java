package com.etienne.client.store.controller;

import com.etienne.client.store.model.auth.OpticsUser;
import com.etienne.client.store.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    @PostMapping(path = "/new")
    @ResponseStatus(CREATED)
    public OpticsUser saveClient(@RequestBody OpticsUser user) {
        return userService.saveUser(user);
    }

}
