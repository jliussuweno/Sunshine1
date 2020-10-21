package com.bca.sunshine1;

public class WeatherModel {
    String day;
    String weatherName;
    String maxDegrees;
    String minDegrees;

    WeatherModel(){

    }

    public WeatherModel(String day, String weatherName, String maxDegrees, String minDegrees){
        this.day = day;
        this.weatherName = weatherName;
        this.maxDegrees = maxDegrees;
        this.minDegrees = minDegrees;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getWeatherName() {
        return weatherName;
    }

    public void setWeatherName(String weatherName) {
        this.weatherName = weatherName;
    }

    public String getMaxDegrees() {
        return maxDegrees;
    }

    public void setMaxDegrees(String maxDegrees) {
        this.maxDegrees = maxDegrees;
    }

    public String getMinDegrees() {
        return minDegrees;
    }

    public void setMinDegrees(String minDegrees) {
        this.minDegrees = minDegrees;
    }


}
