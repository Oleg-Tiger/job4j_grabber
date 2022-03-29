package ru.job4j.solid.srp;

public class Buyer {
    private String name;
    private int id;

    public Buyer(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
