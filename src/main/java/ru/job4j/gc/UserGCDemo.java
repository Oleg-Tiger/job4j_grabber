package ru.job4j.gc;

public class UserGCDemo {
    public static void main(String[] args) {
        User user1 = new User("Oleg", 30);
        User user2 = new User("Rostislav", 25);
        User user3 = new User("Sanya", 33);
        for (int i = 0; i < 18000; i++) {
            new User("name" + i, i);
        }
    }
}
