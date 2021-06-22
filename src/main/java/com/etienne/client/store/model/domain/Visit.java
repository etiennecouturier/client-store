package com.etienne.client.store.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visit implements Comparable<Visit> {

    private LocalDate date;
    private Exam historicExam;
    private Exam exam;
    private Exam contactLenseExam;
    private String shoppingNotes;

    @Override
    public int compareTo(Visit visit) {
        return visit.getDate().compareTo(date);
    }
}
