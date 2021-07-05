package com.etienne.client.store.model.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document
@NoArgsConstructor
public class Visit implements Comparable<Visit> {

    private String id;
    private LocalDate date;
    private Exam historicExam;
    private Exam exam;
    private Exam contactLenseExam;
    private String shoppingNotes;
    private Fees fees;

    public Visit(LocalDate date, Exam historicExam, Exam exam, Exam contactLenseExam, String shoppingNotes, Fees fees) {
        this.id = new ObjectId().toString();
        this.date = date;
        this.historicExam = historicExam;
        this.exam = exam;
        this.contactLenseExam = contactLenseExam;
        this.shoppingNotes = shoppingNotes;
        this.fees = fees;
    }

    @Override
    public int compareTo(Visit visit) {
        return visit.getDate().compareTo(date);
    }
}
