package com.etienne.client.store.model.auth;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Data
@Document
@NoArgsConstructor
public class OpticsUser {
    @Id
    private String id;
    private String username;
    private String email;
    private String password;
    private Set<String> roles = new HashSet<>();

    public OpticsUser(String username, String email, String password, Set<String> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
