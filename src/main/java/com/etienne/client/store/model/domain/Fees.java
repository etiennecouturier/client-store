package com.etienne.client.store.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fees {
    private Integer frame;
    private Integer rightLense;
    private Integer leftLense;
    private Integer service;
    private Integer exam;
    private Integer other;
    private Integer discountPercent;
    private Integer paid;
    private Integer total;
    private Integer discountAmount;
    private Integer toBePaid;
}
