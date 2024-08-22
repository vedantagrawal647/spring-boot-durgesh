package com.springanotation.annotation;

public class User {

    String name;

    public User(String name) {
        this.name = name;
    }

    public void justPrint(){
        System.out.println(this.name+" is justPaying");
    }

}
