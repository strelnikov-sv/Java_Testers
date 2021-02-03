package ru.geekbrains.homework_2;

public class MyArrayDataException extends Exception {
    public int i;
    public int j;

    MyArrayDataException(int i, int j) {
        super("Неверное значение в ячейке: " + i + "x" + j);
        this.i = i;
        this.j = j;
    }

}
