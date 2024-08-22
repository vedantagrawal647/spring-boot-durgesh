package com.example.durgesh5_ServerSideFormValidation.entities;

import jakarta.validation.constraints.*;

public class LoginData {

    @NotBlank(message = "User Name can not be empty !! ")
    @Size(min = 3, max=12,message = "User name must be between 3 - 12 character !!")
    private String username;

    //@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message = "Invalid Email !!")
    @Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message = "Invalid Email !!")
    private String email;

    //agr data true hoga tbhi aayaga wrna error da dega
    @AssertTrue(message = "Must agree term and condition !!")
    private boolean agreed;
    public LoginData() {
        super();
    }

    public LoginData(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAgreed() {
        return agreed;
    }

    public void setAgreed(boolean agreed) {
        this.agreed = agreed;
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
