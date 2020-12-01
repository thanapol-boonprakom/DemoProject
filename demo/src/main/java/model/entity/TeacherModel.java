package model.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TeacherModel implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String first_name;

    private String last_name;

    private String branch;
//    @OneToOne(mappedBy = "teacher", fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    private SubjectModel subjectModel;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}