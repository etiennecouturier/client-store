package com.etienne.client.store.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fees {

    private Double frame;
    private Double rightLense;
    private Double leftLense;
    private Double service;
    private Double exam;
    private Double other;
    private Double discount;
    private Double paid;

}
