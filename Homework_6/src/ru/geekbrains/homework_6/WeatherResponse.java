package ru.geekbrains.homework_6;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class WeatherResponse implements Serializable {
    private Instant datestamp;
    private Integer temperatureMinValue;
    private Integer temperatureMaxValue;
    private String weatherCondition;

    public WeatherResponse(
            Instant datestamp,
            Integer temperatureMinValue,
            Integer temperatureMaxValue,
            String weatherCondition) {
        this.datestamp = datestamp;
        this.temperatureMinValue = temperatureMinValue;
        this.temperatureMaxValue = temperatureMaxValue;
        this.weatherCondition = weatherCondition;
    }

    @Override
    public String toString() {
        return "Дата: " + humanReadableDate(datestamp) +
                ", МинТемп: " + temperatureMinValue +
                ", МаксТемп: " + temperatureMaxValue +
                ", Условия: " + weatherCondition;
    }

    static String humanReadableDate(Instant datestamp){
        return (datestamp).atZone(ZoneId.systemDefault()).toLocalDate().format(
                DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                        .withLocale(new Locale("ru")));
    }


    public Instant getDatestamp() {
        return datestamp;
    }

    public void setDatestamp(Instant datestamp) {
        this.datestamp = datestamp;
    }

    public Integer getTemperatureMaxValue() {
        return temperatureMaxValue;
    }

    public void setTemperatureMaxValue(Integer temperatureMaxValue) {
        this.temperatureMaxValue = temperatureMaxValue;
    }

    public Integer getTemperatureMinValue() {
        return temperatureMinValue;
    }

    public void setTemperatureMinValue(Integer temperatureMinValue) {
        this.temperatureMinValue = temperatureMinValue;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

}

