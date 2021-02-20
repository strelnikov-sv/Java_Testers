package ru.geekbrains.homework_6;

// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

/*
    Реализовать интерфейс общения с пользователем в консоли в формате:
    Назовите город: Выберите опцию:
    -получить погоду на 5 дней
    -выход
    В городе CITY на дату DATE ожидается WEATHER_TEXT, температура - TEMPERATURE - 1 день
    В городе CITY на дату DATE ожидается WEATHER_TEXT, температура - TEMPERATURE - 2 день
    В городе CITY на дату DATE ожидается WEATHER_TEXT, температура - TEMPERATURE - 3 день
    В городе CITY на дату DATE ожидается WEATHER_TEXT, температура - TEMPERATURE - 4 день
    В городе CITY на дату DATE ожидается WEATHER_TEXT, температура - TEMPERATURE - 5 день
    repeat

    Информацию из запросов сериализовать в объект WeatherResponse date weatherText Temperature
    Информацию для сообщения брать из созданного объекта.
 */

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println(WeatherDataSource.getWeatherInCity("Санкт-Петербург"));

    }
}
