package com.example.project;

public class Qna {
    private String title;
    private String content;
    private String writer;
    private String category;
    private boolean isPrivate;
    private String password;  // 비공개 시 비밀번호
    private String date;
    private boolean hasAttachment;

    public Qna(String title, String content, String writer, String category,
               boolean isPrivate, String password, String date, boolean hasAttachment) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.category = category;
        this.isPrivate = isPrivate;
        this.password = password;
        this.date = date;
        this.hasAttachment = hasAttachment;
    }

    // getter
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getWriter() { return writer; }
    public String getCategory() { return category; }
    public boolean isPrivate() { return isPrivate; }
    public String getPassword() { return password; }
    public String getDate() { return date; }
    public boolean hasAttachment() { return hasAttachment; }
}
