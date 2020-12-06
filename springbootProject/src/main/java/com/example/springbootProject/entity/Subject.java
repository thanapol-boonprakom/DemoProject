package com.example.springbootProject.entity;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "subjects")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Subject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String subject_id;
    private String subject_name;
    private String subject_credit;
    private String subject_detail;
//    @Temporal(TemporalType.TIME)
    private LocalTime start_date;
//    @Temporal(TemporalType.TIME)
    private LocalTime end_date;
    private String day;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @ManyToMany(mappedBy = "subjects", fetch = FetchType.LAZY)
    private Set<Student> students = new HashSet<>();

    public Subject() {

    }
    public Subject(Long id){
        this.id = id;
    }

    public Subject(Teacher teacher, String subject_id,
                   String subject_name,
                   String subject_credit,
                   String subject_detail,
                   LocalTime start_date,
                   LocalTime end_date,
                   String day) {
        this.teacher = teacher;
        this.subject_name = subject_name;
        this.subject_credit = subject_credit;
        this.subject_id = subject_id;
        this.subject_detail = subject_detail;
        this.start_date = start_date;
        this.end_date = end_date;
        this.day = day;
    }

    public Long getId() {
        return id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getSubject_detail() {
        return subject_detail;
    }

    public void setSubject_detail(String subject_detail) {
        this.subject_detail = subject_detail;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public LocalTime getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalTime start_date) {
        this.start_date = start_date;
    }

    public LocalTime getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalTime end_date) {
        this.end_date = end_date;
    }

    public String getSubject_credit() {
        return subject_credit;
    }

    public void setSubject_credit(String subject_credit) {
        this.subject_credit = subject_credit;
    }
}
