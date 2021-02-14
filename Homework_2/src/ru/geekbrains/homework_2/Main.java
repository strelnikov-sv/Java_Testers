package ru.geekbrains.homework_2;

/*
1. Написать метод, на вход которому подается двумерный строковый массив размером 4х4.
При подаче массива другого размера необходимо бросить исключение MyArraySizeException.
2. Далее метод должен пройтись по всем элементам массива, преобразовать в int и просуммировать.
Если в каком-то элементе преобразование не удалось (например, если в ячейке лежит символ или текст вместо числа),
надо бросить исключение MyArrayDataException с детализацией, в какой ячейке неверные данные.
3. В методе main() вызвать полученный метод, обработать возможные исключения MySizeArrayException и MyArrayDataException и вывести результат расчета.
 */

public class Main {
    private static final int s = 4;

    public static void main(String[] args) {

        String[][] arr = new String[][]{{"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}};

        try {
            checkDimentionality(arr);
            int[][] matrix = stringToInt2d(arr);
            int sum = sum2d(matrix);
            System.out.println("Сумма значений массива: " + sum);
        } catch (MyArraySizeException e) {
            System.out.println(e);
        } catch (MyArrayDataException e) {
            System.out.println(e);
        }

    }

    public static void checkDimentionality(String[][] arr) throws MyArraySizeException {
        if (arr.length != s) {
            throw new MyArraySizeException();
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length != s) {
                throw new MyArraySizeException();
            }
        }
    }

    public static int[][] stringToInt2d(String[][] arr) throws MyArrayDataException {
        int[][] conv_arr = new int[s][s];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    conv_arr[i][j] = Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        return conv_arr;
    }

    public static int sum2d(int[][] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                count += arr[i][j];
            }
        }
        return count;
    }
}