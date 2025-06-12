package com.example.project;

public class Schedule {
    private String title;
    private String startDate;
    private String endDate;

    // 하루짜리 일정용 생성자
    public Schedule(String title, String date) {
        this.title = title;
        this.startDate = date;
        this.endDate = date; // 기본적으로 시작일과 동일하게 설정
    }

    // 기간 일정용 생성자
    public Schedule(String title, String startDate, String endDate) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = (endDate == null || endDate.isEmpty()) ? startDate : endDate;
    }

    public String getTitle() {
        return title;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        // 혹시라도 외부에서 null로 초기화했을 경우 대비
        return (endDate == null || endDate.isEmpty()) ? startDate : endDate;
    }
}
