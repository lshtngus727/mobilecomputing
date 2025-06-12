package com.example.project;

public class Club {
    public String name;
    public String category;
    public String intro;
    public String leader;
    public String applyInfo;

    public Club(String name, String category, String intro, String leader, String applyInfo) {
        this.name = name;
        this.category = category;
        this.intro = intro;
        this.leader = leader;
        this.applyInfo = applyInfo;
    }

    @Override
    public String toString() {
        return name; // ListView에서 이름만 출력
    }
}
