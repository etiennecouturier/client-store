package com.etienne.client.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visit implements Comparable<Visit> {

    private Date date;
    private Eye rightEye;
    private Eye leftEye;
    private String examNotes;
    private String shoppingNotes;

    @Override
    public int compareTo(Visit visit) {
        return visit.getDate().compareTo(date);
    }
}
