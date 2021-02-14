package ru.geekbrains.homework_5;

/*
Реализовать сохранение данных в csv файл;
Реализовать загрузку данных из csv файла. Файл читается целиком.
Структура csv файла:
Строка заголовок с набором столбцов
Набор строк с целочисленными значениями
Разделитель между столбцами - символ точка с запятой (;)

Пример:
Value 1;Value 2;Value 3
100;200;123
300,400,500

Для хранения данных использовать класс вида:
public class AppData {
private String[] header;
private int[][] data;
// ...
}
 */

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String[] header = {"Value1", "Value2", "Value3"};
        int[][] data = {{100,400,123},{300,400,500}};
        AppData appData = new AppData(header, data);
        MyCSV.writeCSV("./file.csv", appData,",");
        AppData appData1 = (MyCSV.readCSV("./file.csv", ","));
        System.out.println(appData1);
    }
}
