package com.etienne.client.store.model.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * client with a single visit
 */

@Data
@NoArgsConstructor
public class ClientVisit {

    private String id;
    private String name;
    private LocalDate dob;
    private Integer age;
    private String sex;
    private String tel;
    private String email;
    private Visit visits;

}
