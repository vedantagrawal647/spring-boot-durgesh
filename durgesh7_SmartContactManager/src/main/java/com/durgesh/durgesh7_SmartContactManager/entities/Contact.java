package com.durgesh.durgesh7_SmartContactManager.entities;

import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Entity
@Table(name="CONTACT")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cId;
    private String name;
    private String secondName;

    private String work;

    private String email;

    private String phone;

    private String image;

    @Column(length = 1000)
    private String description;

    @ManyToOne()
    @JsonIgnore
    private UserData userData;

    public Contact() {
        super();
    }

    public Contact(int cId, String name, String secondName, String work, String email, String phone, String image, String description) {
        this.cId = cId;
        this.name = name;
        this.secondName = secondName;
        this.work = work;
        this.email = email;
        this.phone = phone;
        this.image = image;
        this.description = description;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "cId=" + cId +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", work='" + work + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public  boolean equals(Object obj)
    {
        return this.cId==((Contact)obj).getcId();
    }
}
