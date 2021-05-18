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

    Client(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
