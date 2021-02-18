package ru.geekbrains.homework_5;

import java.io.*;
import java.util.*;

public class MyCSV {

    public static void writeCSV(String pathname, AppData appData, String sep) {
        File file = new File(pathname);
        appData.setSeperator(sep);
        try (OutputStream out = new FileOutputStream(file)) {
            out.write(appData.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static AppData readCSV(String pathname, String sep) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(pathname));
        // Читаем BufferedReader, поэтому не знаем длины заранее
        String[] header;
        // Поэтому используем структуру данных, которая позволяет сохранять значения по произвольному ключу
        List<int[]> buffer = new ArrayList<>();
        // Читаем заголовок, разбиваем и узнаем количество колонок
        String result = reader.readLine();
        header = result.split(sep);
        int ncols = header.length;
        int lineNumber = 0;

        while ((result = reader.readLine()) != null) {
            String[] tokens = result.split(sep);
            if (tokens.length != ncols) {
                throw new IOException("Строка " + (lineNumber + 2) + " содержит " + tokens.length + " токенов. Ожидалось " + ncols);
            }
            buffer.add(lineNumber, new int[ncols]);
            for (int i = 0; i < ncols; i++) {
                buffer.get(lineNumber)[i] = Integer.parseInt(tokens[i]);
            }
            lineNumber++;
        }
        int nrows = buffer.size();
        int[][] data = new int[nrows][ncols]; // Когда знаем количество рядов, можно инициализировать в нужном формате
        for (int i = 0; i < nrows; i++) {
            data[i] = buffer.get(i);
        }
        AppData res = new AppData(header, data);
        res.setSeperator(sep);
        return res;
    }
}
