package com.etienne.client.store.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

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
    private List<Visit> visits;

    public Client(String name, String dob, String tel,
                  String email, List<Visit> visits) {
        this.name = name;
        this.dob = dob;
        this.tel = tel;
        this.email = email;
        this.visits = visits;
    }
}
