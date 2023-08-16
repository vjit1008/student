package com.neebal.entities;

import javax.persistence.*;


import java.util.Set;

@Entity
@Table(name = "exams")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String name;


    @Column(nullable = false)
    private Double duration;


    @Column(nullable = true)
    private Long totalMarks;

    @OneToMany(mappedBy = "exam")
    private Set<ExamQuestion> examQuestionSet;

    @OneToMany(mappedBy = "exam")
    private Set<StudentsExams> studentsExamsSet;

    public Exam() {
    }

    public Exam(String name, Double duration, Long totalMarks) {
        this.name = name;
        this.duration = duration;
        this.totalMarks = totalMarks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Long getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(Long totalMarks) {
        this.totalMarks = totalMarks;
    }

    public Set<ExamQuestion> getExamQuestionSet() {
        return examQuestionSet;
    }

    public void setExamQuestionSet(Set<ExamQuestion> examQuestionSet) {
        this.examQuestionSet = examQuestionSet;
    }

    public Set<StudentsExams> getStudentExamSet() {
        return studentsExamsSet;
    }

    public void setStudentExamSet(Set<StudentsExams> studentsExamsSet) {
        this.studentsExamsSet = studentsExamsSet;
    }
}






