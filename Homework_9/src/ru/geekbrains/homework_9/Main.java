package ru.geekbrains.homework_9;

/*
- Написать функцию, принимающую список Student и возвращающую список уникальных курсов,
    на которые подписаны студенты.
- Написать функцию, принимающую на вход список Student и возвращающую список из трех
    самых любознательных (любознательность определяется количеством курсов).
- Написать функцию, принимающую на вход список Student и экземпляр Course,
    возвращающую список студентов, которые посещают этот курс.
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private static final Integer LIMIT = 3;

    static List<String> uniqueCourses(Stream<Student> students) {
        return students.map(Student::getAllCourses)
                .flatMap(List::stream)
                .map(Course::getName)
                .distinct()
                .collect(Collectors.toList());
    }

    static List<String> mostEducated(Stream<Student> students) {
        return students
                .sorted(Comparator.comparingInt(o -> o.getAllCourses().size() * -1))
                .limit(LIMIT)
                .map(Student::getName)
                .collect(Collectors.toList());
    }

    static List<String> studentCourses(Stream<Student> students, Course SetCourse) {
        return students.filter(student -> student.getAllCourses().stream().anyMatch(
                course -> course.name == SetCourse.name))
                .map(Student::getName)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>(Arrays.asList(
                new Student("Sergey", (Arrays.asList(new Course("Math"),
                        new Course("Bio"),
                        new Course("History"),
                        new Course("English")))),
                new Student("Ivan",  (Arrays.asList(
                        new Course("Math"),
                        new Course("History"),
                        new Course("English")))),
                new Student("Max", (Arrays.asList(
                        new Course("Bio"),
                        new Course("History"),
                        new Course("English")))),
                new Student("Lisa", (Arrays.asList(
                        new Course("Math"),
                        new Course("History"))))));

        List<String> unique = uniqueCourses(students.stream());
        Course setCourse = new Course("Math");
        List<String> studCourses = studentCourses(students.stream(), setCourse);
        List<String> mostEducated = mostEducated(students.stream());
        System.out.println("Уникальные предметы: " + unique);
        System.out.println("Любознательные студенты:" + mostEducated);
        System.out.println("Список студентов посещающих курс: " + studCourses);
    }

}
