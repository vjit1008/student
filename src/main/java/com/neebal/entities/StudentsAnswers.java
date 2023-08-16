package com.neebal.entities;


import javax.persistence.*;

@Entity
@Table(name = "students_answers")
public class StudentsAnswers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private StudentsExams studentsExams;

    @ManyToOne
    private Question question;

    @ManyToOne
    private QuestionOptions chosenOption;

    public StudentsAnswers(){}

    public StudentsAnswers(Question question, QuestionOptions chosenOption) {
        this.question = question;
        this.chosenOption = chosenOption;
    }

    public StudentsExams getStudentExam() {
        return studentsExams;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StudentsExams getStudentsExams() {
        return studentsExams;
    }

    public void setStudentsExams(StudentsExams studentsExams) {
        this.studentsExams = studentsExams;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public QuestionOptions getChosenOption() {
        return chosenOption;
    }

    public void setChosenOption(QuestionOptions chosenOption) {
        this.chosenOption = chosenOption;
    }
}