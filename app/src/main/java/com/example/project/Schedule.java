package com.example.project;

public class Schedule {
    private String title;
    private String date; // yyyy-MM-dd 형식

    public Schedule(String title, String date) {
        this.title = title;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }
}
