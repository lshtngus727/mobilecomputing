package com.example.project;

public class Day {
    private int dayNumber;
    private boolean isCurrentMonth;

    public Day(int dayNumber, boolean isCurrentMonth) {
        this.dayNumber = dayNumber;
        this.isCurrentMonth = isCurrentMonth;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public boolean isCurrentMonth() {
        return isCurrentMonth;
    }
}
