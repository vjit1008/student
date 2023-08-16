package com.neebal.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "exams_questions")
public class ExamQuestion implements Serializable {

    @Id
    @ManyToOne
    private Exam exam;

    @Id
    @ManyToOne
    private Question question;

    public ExamQuestion() {
    }

    public ExamQuestion(Exam exam, Question question) {
        this.exam = exam;
        this.question = question;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }


}
