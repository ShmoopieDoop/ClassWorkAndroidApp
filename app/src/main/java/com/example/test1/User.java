package com.example.test1;

import androidx.annotation.NonNull;

public class User {
    private String id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String gender;
    private String allowContact;

    public User(String id, String username, String password, String phone, String email, String gender, String allowContact) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.allowContact = allowContact;
    }

    public User() {
        this.id = "";
        this.username = "";
        this.password = "";
        this.phone = "";
        this.email = "";
        this.gender = "";
        this.allowContact = "";
    }

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + "'\n" +
                ", username='" + username + "'\n" +
                ", password='" + password + "'\n" +
                ", phone='" + phone + "'\n" +
                ", email='" + email + "'\n" +
                ", gender='" + gender + "'\n" +
                ", allowContact='" + allowContact + "'\n" +
                '}';
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getAllowContact() {
        return allowContact;
    }
    public void setAllowContact(String allowContact) {
        this.allowContact = allowContact;
    }
}
