package com.neebal.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "students_exams")
public class StudentsExams implements Serializable {

    @Id
    @ManyToOne
    private Exam exam;

    @Id
    @ManyToOne
    private Student student;

    @Column(nullable = true)
    private Long score;

    @Temporal(TemporalType.DATE)
    @Column(nullable = true)
    private Date date;

    @OneToMany(mappedBy = "studentsExams", cascade = CascadeType.ALL)
    private List<StudentsAnswers> studentsAnswers = new ArrayList<>();

    public StudentsExams() {
    }

    public StudentsExams(Exam exam, Student student, Long score, Date date) {
        this.exam = exam;
        this.student = student;
        this.score = score;
        this.date = date;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<StudentsAnswers> getStudentsAnswers() {
        return studentsAnswers;
    }

    public void setStudentsAnswers(List<StudentsAnswers> studentsAnswers) {
        this.studentsAnswers = studentsAnswers;
    }
}
