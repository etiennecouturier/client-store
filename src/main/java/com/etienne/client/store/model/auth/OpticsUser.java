package com.etienne.client.store.model.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class OpticsUser {
    @Id
    private String username;
    private String email;
    private String password;
    private Set<String> roles = new HashSet<>();
}
