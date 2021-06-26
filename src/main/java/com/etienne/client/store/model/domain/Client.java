package com.etienne.client.store.model.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Document
@NoArgsConstructor
public class Client {
    @Id
    private String id;
    private String name;
    private LocalDate dob;
    private Integer age;
    private String sex;
    private String tel;
    private String email;
    private List<Visit> visits;

    public Client(String name, LocalDate dob, Integer age,
                  String tel, String email, List<Visit> visits) {
        this.name = name;
        this.dob = dob;
        this.age = age;
        this.tel = tel;
        this.email = email;
        this.visits = visits;
    }

    public String getFirstName() {
        return name.substring(name.lastIndexOf(" ") + 1);
    }
}
