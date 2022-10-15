package com.example.zaliczenieklient2;

public class MarkTable {

    private String subject;
    private String marks;
    private Double avgMarks;

    public MarkTable(String subject, String marks, Double avgMarks) {
        this.subject = subject;
        this.marks = marks;
        this.avgMarks = avgMarks;
    }

    public String getSubject() {
        return subject;
    }

    public String getMarks() {
        return marks;
    }

    public Double getAvgMarks() {
        return avgMarks;
    }
}
