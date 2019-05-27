package com.example.gpaplanner;

import java.util.List;

public class Student {

    private String name = null;
    private String username = null;
    private String password = null;
    private String major = null;
    private List<Course> courseTaken;

    public Student (){

    }

    public Student (String name, String username, String password, String major){

        this.name = name;
        this.username = username;
        this.password = password;
        this.major = major;

    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public List<Course> getCourseTaken() {
        return courseTaken;
    }
}
