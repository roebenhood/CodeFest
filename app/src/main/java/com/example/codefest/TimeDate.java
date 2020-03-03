package com.example.codefest;

public class TimeDate {

    public TimeDate() {
    }

    private int id;
    private String date_time;
    private String time;

    public TimeDate(String date_time, String time) {
        this.date_time = date_time;
        this.time = time;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
