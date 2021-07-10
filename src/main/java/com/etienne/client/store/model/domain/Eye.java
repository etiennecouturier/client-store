package com.etienne.client.store.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Eye {

    private Double dioptria;
    private Double cilinder;
    private Double fok;
    private Double vizus;
    private Double szaruGorbulet;
    private Double add;
    private Double pd;
    private Double bifoMag;

}
