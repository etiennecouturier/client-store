package com.etienne.client.store;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
class Client {
    @Id
    private String id;
    private String name;
    private Integer age;
    private Integer height;
    private String eyeColor;
    private Address address;

    public Client(String name, Integer age, Integer height, String eyeColor, Address address) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.eyeColor = eyeColor;
        this.address = address;
    }
}
