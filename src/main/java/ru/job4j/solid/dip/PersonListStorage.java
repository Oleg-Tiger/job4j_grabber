package ru.job4j.solid.dip;

import java.util.ArrayList;
import java.util.List;

/**
 * Данный класс - хранилище объектов Person. Предоставляет возможность добавлять и удалять объект Person.
 */
public class PersonListStorage {
    private final List<Person> list = new ArrayList<>();

    public List<Person> getList() {
        return List.copyOf(list);
    }

    public boolean addPerson(Person person) {

        return true;
    }

    public boolean removePerson(Person person) {

        return true;
    }

}
