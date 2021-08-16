package com.etienne.client.store.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Data
@Document
@NoArgsConstructor
public class Visit implements Comparable<Visit> {

    private String id;
    private long seq;
    private LocalDateTime date;
    private Exam historicExam;
    private Exam exam;
    private Exam contactLenseExam;
    private Fees fees;
    private OtherInfo otherInfo;

    public Visit(LocalDateTime date, Exam historicExam, Exam exam, Exam contactLenseExam, Fees fees,
                 OtherInfo otherInfo) {
        this.id = new ObjectId().toString();
        this.date = date;
        this.historicExam = historicExam;
        this.exam = exam;
        this.contactLenseExam = contactLenseExam;
        this.fees = fees;
        this.otherInfo = otherInfo;
    }

    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    public LocalDateTime getDate() {
        return date;
    }

    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public int compareTo(Visit visit) {
        return visit.getDate().compareTo(date);
    }
}
