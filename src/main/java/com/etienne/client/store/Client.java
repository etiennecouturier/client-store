package com.etienne.client.store;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
public class Client {
    @Id
    private String id;
    private String name;
    private String dob;
    private String tel;
    private String email;

    public Client(String name, String dob, String tel, String email) {
        this.name = name;
        this.dob = dob;
        this.tel = tel;
        this.email = email;
    }

    public Client(String name) {
        this.name = name;
    }
}
