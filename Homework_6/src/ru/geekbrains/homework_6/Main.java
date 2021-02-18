package ru.geekbrains.homework_6;

/*
С помощью http запроса получить в виде json строки погоду в Санкт-Петербурге на период времени,
пересекающийся со следующим занятием (например, выборка погода на следующие 5 дней - подойдет)
 */

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println(WeatherDataSource.getWeatherInCity("Санкт-Петербург"));

    }
}
