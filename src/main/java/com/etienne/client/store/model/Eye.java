package com.etienne.client.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Eye {

    private Double dioptria;
    private Double cilinder;
    private Integer fok;
    private Double vizus;

}
