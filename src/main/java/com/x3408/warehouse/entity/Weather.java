package com.x3408.warehouse.entity;


import java.util.List;

public class Weather {
    private String update_time;
    private String city;
    private List<WeatherDetail> data;

    @Override
    public String toString() {
        return "Weather{" +
                "update_time='" + update_time + '\'' +
                ", city='" + city + '\'' +
                ", data=" + data +
                '}';
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<WeatherDetail> getData() {
        return data;
    }

    public void setData(List<WeatherDetail> data) {
        this.data = data;
    }
}
