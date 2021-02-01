package ru.geekbrains.homework_1;

public class Wall implements Obstacles{
    private int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public int parameter() {
        return this.height;
    }
}
