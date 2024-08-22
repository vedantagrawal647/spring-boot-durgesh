package com.dugesh.durgesh11_Mapping.entities.one_to_one;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Degree {

    @Id
    private int degree_id;
    private String course;

    @OneToOne
    private Student student;

    // Constructors
    public Degree() {}

    public Degree(int degree_id, String course,Student student) {
        this.degree_id = degree_id;
        this.course = course;
        this.student=student;
    }

    // Getters and setters
    public int getDegree_id() {
        return degree_id;
    }

    public void setDegree_id(int degree_id) {
        this.degree_id = degree_id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
