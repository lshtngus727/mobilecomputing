package com.example.project;
public class File {
    private String title;
    private String clubName;
    private String uploadedDate;
    private String category;
    private String downloadUrl;

    public File(String title, String clubName, String uploadedDate, String category, String downloadUrl) {
        this.title = title;
        this.clubName = clubName;
        this.uploadedDate = uploadedDate;
        this.category = category;
        this.downloadUrl = downloadUrl;
    }

    public String getTitle() { return title; }
    public String getClubName() { return clubName; }
    public String getUploadedDate() { return uploadedDate; }
    public String getCategory() { return category; }
    public String getDownloadUrl() { return downloadUrl; }
}
