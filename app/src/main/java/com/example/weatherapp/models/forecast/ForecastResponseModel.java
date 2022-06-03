package com.example.weatherapp.models.forecast;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForecastResponseModel {

    @SerializedName("city")
    private com.example.weatherapp.models.forecast.City city;

    @SerializedName("cnt")
    private int cnt;

    @SerializedName("cod")
    private String cod;

    @SerializedName("message")
    private int message;

    @SerializedName("list")
    private List<com.example.weatherapp.models.forecast.ListItem> list;

    public void setCity(com.example.weatherapp.models.forecast.City city) {
        this.city = city;
    }

    public com.example.weatherapp.models.forecast.City getCity() {
        return city;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getCod() {
        return cod;
    }

    public void setMessage(int message) {
        this.message = message;
    }

    public int getMessage() {
        return message;
    }

    public void setList(List<com.example.weatherapp.models.forecast.ListItem> list) {
        this.list = list;
    }

    public List<com.example.weatherapp.models.forecast.ListItem> getList() {
        return list;
    }
}