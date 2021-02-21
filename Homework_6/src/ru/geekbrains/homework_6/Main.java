package ru.geekbrains.homework_6;

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

public class Main {

    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        userInterface.runApplication();
    }
}
