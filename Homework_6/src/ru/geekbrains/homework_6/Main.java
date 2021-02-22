package ru.geekbrains.homework_6;

/*
Добавить поддержку SQLite в проект
Создать класс-репозиторий, отвечающий за взаимодействие с базой данных
Организовать запись данных в базу при каждом успешном API запросе.
Формат - String city, String localDate, String weatherText, Double temperature
Организовать чтение из базы всех данных по пункту меню (требует переработки меню)
Учесть, что соединение всегда нужно закрывать
Сдать задание ПУЛРЕКВЕСТ + файл с базой данных .db
 */

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        DatabaseRepositorySQLiteImpl databaseRepository = new DatabaseRepositorySQLiteImpl();
        databaseRepository.createTableIfNotExists();
        UserInterface userInterface = new UserInterface();
        try{
            userInterface.runApplication();
            databaseRepository.getConnection().close();
        } catch (SQLException e){
            System.out.println("SQLException");
        }
    }
}
