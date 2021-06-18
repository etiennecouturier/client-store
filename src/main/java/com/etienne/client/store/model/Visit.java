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
    private Exam historicExam;
    private Exam exam;
    private Exam contactLenseExam;
    private String shoppingNotes;

    @Override
    public int compareTo(Visit visit) {
        return visit.getDate().compareTo(date);
    }
}
