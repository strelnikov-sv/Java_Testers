package ru.geekbrains.homework_1;

public class Treadmill implements Obstacles{
    private int length;

    public Treadmill(int length) {
        this.length = length;
    }

    @Override
    public int parameter() {
        return this.length;
    }
}
