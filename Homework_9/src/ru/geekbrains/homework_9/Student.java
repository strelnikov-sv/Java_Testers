package ru.geekbrains.homework_9;

import java.util.List;

public class Student implements StudentInterface {
    public final String name;
    public final List<Course> course;

    public Student(String name, List<Course> course) {
        this.name = name;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public List<Course> getAllCourses() {
        return course;
    }

}
