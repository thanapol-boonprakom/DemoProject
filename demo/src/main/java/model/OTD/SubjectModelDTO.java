package model.OTD;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalTime;
import java.util.Date;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SubjectModelDTO {
    private Long id;
    private String subject_id;
    private String subject_name;
    private String subject_credit;
    private String subject_detail;
    private Long teacher_id;
//    @Temporal(TemporalType.TIME)
    private LocalTime start_date;
//    @Temporal(TemporalType.TIME)
    private LocalTime end_date;
    private String day;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Long getId() {
        return id;
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

    public Long getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Long teacher_id) {
        this.teacher_id = teacher_id;
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
