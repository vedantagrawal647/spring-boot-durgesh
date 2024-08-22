package com.dugesh.durgesh11_Mapping.entities.one_to_one;

import com.dugesh.durgesh11_Mapping.entities.one_to_one.Degree;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Student {



    @Id
    private int student_id;

    private String firstName;
    private String lastName;

    @OneToOne(mappedBy = "student" ,cascade = CascadeType.ALL)
    private Degree degree;


    public Student() {
    }

    public Student(int student_id, String firstName, String lastName, Degree degree) {
        this.student_id = student_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.degree = degree;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }
}
