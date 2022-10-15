package com.example.zaliczenieklient2;

public class LessonsTable {
    private String hour;
    private String monday;
    private String thesday;
    private String wednesday;
    private String thursday;
    private String friday;

    public LessonsTable(String hour, String monday, String thesday, String wednesday, String thursday, String friday) {
        this.hour = hour;
        this.monday = monday;
        this.thesday = thesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
    }

    public String getHour() {
        return hour;
    }

    public String getMonday() {
        return monday;
    }

    public String getThesday() {
        return thesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public String getFriday() {
        return friday;
    }
}
