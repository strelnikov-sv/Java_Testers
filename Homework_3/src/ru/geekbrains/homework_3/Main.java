package ru.geekbrains.homework_3;

/*
Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);

Задача:
-Даны классы: Fruit, Apple extends Fruit, Orange extends Fruit.
-Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
-Для хранения фруктов внутри коробки использовать ArrayList
-Сделать метод getWeight(), который высчитывает вес коробки, зная вес одного фрукта и их количество: вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);
-Внутри класса Box сделать метод compare(), который позволяет сравнить текущую коробку с той, которую подадут в compare()
    в качестве параметра. true – если их массы равны, false в противоположном случае. Можно сравнивать коробки с яблоками и апельсинами;
-Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую. Помним про сортировку фруктов:
    нельзя яблоки высыпать в коробку с апельсинами. Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в первой;
-Не забываем про метод добавления фрукта в коробку.
 */

import java.util.Arrays;

public class Main {
    final static Integer[] arr_int = {1, 2, 3, 4, 5};
    final static String[] arr_str = {"a", "b", "c"};

    public static <T> void safeArraySwitch(T[] arr, int idx1, int idx2) {
        T swap;
        try {
            swap = arr[idx1];
            arr[idx1] = arr[idx2];
            arr[idx2] = swap;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Индекс вышел за пределы массива");
        }
    }

    public static void main(String[] args) {
        System.out.println("Исходный массив: " + Arrays.toString(arr_int));
        safeArraySwitch(arr_int, 0, 1);
        System.out.println("Результат: " + Arrays.toString(arr_int)+ "\n");

        System.out.println("Исходный массив: " + Arrays.toString(arr_str));
        safeArraySwitch(arr_str, 0, 1);
        System.out.println("Результат: " + Arrays.toString(arr_str)+ "\n");

        Box<Apple> appleBox = new Box<>();
        Box<Orange> orangeBox = new Box<>();
        appleBox.put(new Apple());
        appleBox.put(new Apple());
        appleBox.put(new Apple());
        appleBox.put(new Apple());
        orangeBox.put(new Orange());
        orangeBox.put(new Orange());

        System.out.println(appleBox.getWeight());
        System.out.println(orangeBox.getWeight());
        System.out.println(orangeBox.compare(appleBox) + "\n");

        Box<Apple> appleBox2 = new Box<>();
        System.out.println("appleBox : " + appleBox.toString());
        System.out.println("appleBox2 : " + appleBox2.toString() + "\n");

        appleBox.unloadTo(appleBox2);
        System.out.println("appleBox : " + appleBox.toString());
        System.out.println("appleBox2 : " + appleBox2.toString());

     //   appleBox2.unloadTo(orangeBox); // ошибка типов cannot be converted

    }


}
