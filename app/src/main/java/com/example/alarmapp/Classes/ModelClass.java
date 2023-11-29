package com.example.alarmapp.Classes;

public class ModelClass {
   private int hour;
   private int min;
   private String am;
   private int year;
   private int month;
   private int day;
   private long time;
   private boolean isOn;

    public ModelClass(int hour, int min, String am, int year, int month, int day, long time, boolean isOn) {
        this.hour = hour;
        this.min = min;
        this.am = am;
        this.year = year;
        this.month = month;
        this.day = day;
        this.time = time;
        this.isOn = isOn;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public String getAm() {
        return am;
    }

    public void setAm(String am) {
        this.am = am;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }
}
