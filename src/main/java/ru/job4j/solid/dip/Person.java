package ru.job4j.solid.dip;

import java.util.Objects;

public class Person {
    String name;
    String surname;
    int id;

    public Person(String name, String surname, int id) {
        this.name = name;
        this.surname = surname;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return id == person.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getId() {
        return id;
    }
}
