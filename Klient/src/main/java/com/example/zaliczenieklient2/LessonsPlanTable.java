package com.example.zaliczenieklient2;

public class LessonsPlanTable {
    String monday,tuesday,thursday,wendesday,friday;

    public LessonsPlanTable(String monday, String tuesday, String thursday, String wendesday, String friday) {
        this.monday = monday;
        this.tuesday = tuesday;
        this.thursday = thursday;
        this.wendesday = wendesday;
        this.friday = friday;
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public String getWendesday() {
        return wendesday;
    }

    public void setWendesday(String wendesday) {
        this.wendesday = wendesday;
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }
}
