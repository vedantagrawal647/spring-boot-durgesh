package com.xmlFormat.xmlFormatData.entity;

import lombok.Data;

@Data
public class Student {

    private int id;
    private String name;
    private int rollNo;
    private String favColor;

    public Student(int id, String name, int rollNo, String favColor) {
        this.id = id;
        this.name = name;
        this.rollNo = rollNo;
        this.favColor = favColor;
    }
}
