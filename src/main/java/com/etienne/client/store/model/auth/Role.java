package com.etienne.client.store.model.auth;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
public class Role {
    @Id
    private String id;
    private RoleEnum name;
}
