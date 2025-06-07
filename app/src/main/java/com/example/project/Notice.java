package com.example.project;

public class Notice {
    private String title;
    private String content;
    private String date;
    private boolean isFixed;
    private boolean hasAttachment;

    public Notice(String title, String content, String date, boolean isFixed, boolean hasAttachment) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.isFixed = isFixed;
        this.hasAttachment = hasAttachment;
    }

    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getDate() { return date; }
    public boolean isFixed() { return isFixed; }
    public boolean hasAttachment() { return hasAttachment; }
}