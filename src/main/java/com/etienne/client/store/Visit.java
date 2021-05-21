package com.etienne.client.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visit {

    private String date;
    private Eye rightEye;
    private Eye leftEye;
    private String notes;

}
