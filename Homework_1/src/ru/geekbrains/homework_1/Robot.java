package ru.geekbrains.homework_1;

public class Robot implements Actionable {
    int jump_height;
    int run_length;

    public boolean run(int run_length) {
        if (this.run_length >= run_length) {
            System.out.println("Robot is running " + run_length);
            return true;
        } else {
            System.out.println("Robot cannot run " + run_length);
            return false;
        }
    }

    public boolean jump(int jump_height) {
        if (this.jump_height >= jump_height) {
            System.out.println("Robot is jumping " + jump_height);
            return true;
        } else {
            System.out.println("Robot cannot jump " + jump_height);
            return false;
        }
    }

    public Robot(int jump_height, int run_length) {
        this.jump_height = jump_height;
        this.run_length = run_length;
    }
}