package com.example.alarmapp.Classes;

public class ModelClass {
   private int hour;
   private int min;
   private String am;
   private int year;
   private int month;
   private int day;

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

    public ModelClass(int hour, int min, String am, int year, int month, int day) {
        this.hour = hour;
        this.min = min;
        this.am = am;
        this.year = year;
        this.month = month;
        this.day = day;
    }
}
