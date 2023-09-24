package com.example.lms;

public class Purchase {
    private String purchased_course;
    private String purchased_mentor;

    public Purchase() {
    }

    public Purchase(String purchased_course, String purchased_mentor) {
        this.purchased_course = purchased_course;
        this.purchased_mentor = purchased_mentor;
    }

    public String getPurchased_course() {
        return purchased_course;
    }

    public void setPurchased_course(String purchased_course) {
        this.purchased_course = purchased_course;
    }

    public String getPurchased_mentor() {
        return purchased_mentor;
    }

    public void setPurchased_mentor(String purchased_mentor) {
        this.purchased_mentor = purchased_mentor;
    }
}
