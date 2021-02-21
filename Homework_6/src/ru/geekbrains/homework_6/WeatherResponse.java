package ru.geekbrains.homework_6;

import java.io.Serializable;

public class WeatherResponse implements Serializable {
    private String datestamp;
    private String temperatureMinValue;
    private String temperatureMaxValue;
    private String weatherCondition;

    public WeatherResponse(
            String datestamp,
            String temperatureMinValue,
            String temperatureMaxValue,
            String weatherCondition) {
        this.datestamp = datestamp;
        this.temperatureMinValue = temperatureMinValue;
        this.temperatureMaxValue = temperatureMaxValue;
        this.weatherCondition = weatherCondition;
    }

    @Override
    public String toString() {
        return "Дата: " + datestamp +
                ", МинТемп: " + temperatureMinValue +
                ", МаксТемп: " + temperatureMaxValue +
                ", Условия: " + weatherCondition;
    }

    public String getDatestamp() {
        return datestamp;
    }

    public void setDatestamp(String datestamp) {
        this.datestamp = datestamp;
    }

    public String getTemperatureMaxValue() {
        return temperatureMaxValue;
    }

    public void setTemperatureMaxValue(String temperatureMaxValue) {
        this.temperatureMaxValue = temperatureMaxValue;
    }

    public String getTemperatureMinValue() {
        return temperatureMinValue;
    }

    public void setTemperatureMinValue(String temperatureMinValue) {
        this.temperatureMinValue = temperatureMinValue;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

}

