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
    private Fees fees;
    private String frame;
    private String lense;

    public Visit(LocalDate date, Exam historicExam, Exam exam, Exam contactLenseExam, Fees fees,
                 String frame, String lense) {
        this.id = new ObjectId().toString();
        this.date = date;
        this.historicExam = historicExam;
        this.exam = exam;
        this.contactLenseExam = contactLenseExam;
        this.fees = fees;
        this.frame = frame;
        this.lense = lense;
    }

    @Override
    public int compareTo(Visit visit) {
        return visit.getDate().compareTo(date);
    }
}
