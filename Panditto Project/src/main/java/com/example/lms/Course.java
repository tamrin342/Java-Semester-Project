package com.example.lms;

public class Course {
    private String course_title;
    private String mentor;
    private double price;

    public Course() {
    }

    public Course(String course_title, String mentor, double price) {
        this.course_title = course_title;
        this.mentor = mentor;
        this.price = price;
    }

    public String getCourse_title() {
        return course_title;
    }

    public void setCourse_title(String course_title) {
        this.course_title = course_title;
    }

    public String getMentor() {
        return mentor;
    }

    public void setMentor(String mentor) {
        this.mentor = mentor;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
