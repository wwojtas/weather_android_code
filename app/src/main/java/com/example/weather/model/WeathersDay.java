package com.example.weather.model;

public class WeathersDay {
    private String date;
    private String icon;
    private String description;
    private String dayTemperature;

    public WeathersDay(String date, String icon, String description, String dayTemperature) {
        this.date = date;
        this.icon = icon;
        this.description = description;
        this.dayTemperature = dayTemperature;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDayTemperature() {
        return dayTemperature;
    }

    public void setDayTemperature(String dayTemperature) {
        this.dayTemperature = dayTemperature;
    }


}
