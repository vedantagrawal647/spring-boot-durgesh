package com.springanotation.annotation.exception;

public class A {

      A obj;


    public  A m1() {
        if(obj == null) {
            obj = new A();
        }
        return obj;
    }

    public static void main(String[] args) {

    }
}
