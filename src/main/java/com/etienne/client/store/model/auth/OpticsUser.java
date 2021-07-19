package com.etienne.client.store.model.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
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
//    private String email;
    private String password;
//    @DBRef
//    private Set<Role> roles = new HashSet<>();


    public OpticsUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
