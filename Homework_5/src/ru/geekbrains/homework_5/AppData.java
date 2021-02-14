package ru.geekbrains.homework_5;

import java.io.Serializable;

public class AppData implements Serializable {
    final String[] header;
    final int[][] data;
    public String separator;

    public AppData(String[] header, int[][] data) {
        this.header = header;
        this.data = data;
        this.separator = ";";
    }

    @Override
    public String toString() {
        String eol = "\n";
        StringBuilder outputData = new StringBuilder(String.join(separator, header) + eol);
        for (int[] row : data) {
            for (int i = 0; i < row.length; i++) {
                outputData.append(row[i]);
                if (i != row.length - 1) {
                    outputData.append(separator);
                }
            }
            outputData.append(eol);
        }
        return outputData.toString();
    }

    public void setSeperator(String s) {
        this.separator = s;
    }

}
