package ru.geekbrains.homework_2;

public class MyArraySizeException extends Exception {
    MyArraySizeException() {
        super("Размер массива отличается от 4x4");
    }
}