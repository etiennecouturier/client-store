package com.etienne.client.store.model.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
public class NameSex {
    @Id
    private String lastName;
    private Sex sex;
}

enum Sex {MALE, FEMALE}

