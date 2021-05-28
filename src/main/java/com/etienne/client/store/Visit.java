package com.etienne.client.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visit {

    private Date date;
    private Eye rightEye;
    private Eye leftEye;
    private String notes;

}
