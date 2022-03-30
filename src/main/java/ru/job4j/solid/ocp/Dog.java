package ru.job4j.solid.ocp;

public class Dog {
    private int id;
    private String breed;
    private String name;


    public Dog(String breed, String name) {
        this.breed = breed;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
