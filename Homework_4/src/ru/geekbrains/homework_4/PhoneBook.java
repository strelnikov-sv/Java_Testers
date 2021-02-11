package ru.geekbrains.homework_4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PhoneBook {

    final private Map<String, ArrayList<String>> phonebook = new HashMap<>();

    public void add(String name, String number) {
        ArrayList<String> list = phonebook.getOrDefault(name, new ArrayList<>());
        list.add(number);
        phonebook.put(name, list);
    }

    public ArrayList<String> get(String name) {
        return phonebook.get(name);

    }
}