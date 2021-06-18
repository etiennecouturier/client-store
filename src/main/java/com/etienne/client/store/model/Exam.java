package com.etienne.client.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exam {
    private Eye rightEye;
    private Eye leftEye;
    String notes;
}
