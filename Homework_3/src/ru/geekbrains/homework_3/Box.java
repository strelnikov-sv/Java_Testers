package ru.geekbrains.homework_3;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    public ArrayList<T> fruits = new ArrayList();

    public void put(T fruit) {
        fruits.add(fruit);
    }

    public float getWeight() {
        float weight = 0;
        for (T fruit : fruits) {
            weight += fruit.getWeight();
        }
        return weight;
    }

    public boolean compare(Box<?> box) {
        return this.getWeight() == box.getWeight();
    }

    public void unloadTo(Box<T> box) {
        for (T fruit : fruits) {
            box.put(fruit);
        }
        fruits = new ArrayList<T>();
    }

    @Override
    public String toString() {
        String content = "";
        for (T fruit : fruits) {
            content += fruit.toString() + " ";
        }
        return "Box { fruits = " + content +
//                " weight = " + this.getWeight() +
                '}';
    }


}
