package ru.geekbrains.homework_1;

/*
Создайте три класса Человек, Кот, Робот, которые не наследуются от одного класса.
Эти классы должны уметь бегать и прыгать (методы просто выводят информацию о действии в консоль).

Создайте два класса: беговая дорожка и стена, при прохождении через которые, участники должны выполнять соответствующие действия (бежать или прыгать),
результат выполнения печатаем в консоль (успешно пробежал, не смог пробежать и т.д.).
Создайте два массива: с участниками и препятствиями, и заставьте всех участников пройти этот набор препятствий.

* У препятствий есть длина (для дорожки) или высота (для стены), а участников ограничения на бег и прыжки.
Если участник не смог пройти одно из препятствий, то дальше по списку он препятствий не идет.
 */

public class Main {
    public static void main(String[] args) {


        Actionable[] agents = new Actionable[]{
                new Cat(5, 6),
                new Human(8, 12),
                new Robot(15, 15)};

        Obstacles[] obstacles = new Obstacles[]{
                new Wall(4),
                new Treadmill(3),
                new Wall(7),
                new Treadmill(7),
                new Wall(10),
                new Treadmill(10)
        };
        for (Obstacles obstacle : obstacles) {
            for (int i = 0; i < agents.length; i++) {
                if (agents[i] == null) {
                    continue;
                }
                boolean sucs;
                if (obstacle instanceof Wall) {
                    sucs = agents[i].jump(obstacle.parameter());
                } else {
                    sucs = agents[i].run(obstacle.parameter());
                }
                if (!sucs) {
                    agents[i] = null;
                }
            }
            System.out.println("----------------------------------------");
        }
    }
}
