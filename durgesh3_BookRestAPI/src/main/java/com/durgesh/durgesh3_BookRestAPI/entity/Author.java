package com.durgesh.durgesh3_BookRestAPI.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int authorId;

    @Column(name = "first_name")
    private String firstname;
    private String lastname;
    private String language;

    //bidirectional
    @OneToOne(mappedBy = "author")
    @JsonBackReference
    private Book book;


    public Author() {
        super();
    }

    public Author(int authorId, String firstname, String lastname, String language) {
        this.authorId = authorId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.language = language;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
