package ru.job4j.solid.ocp;

import java.util.HashMap;
import java.util.Map;

/**
 * Данный класс - приют для собак. Нарушение принципа OCP в том, что нужно работать с абстракциями.
 * В нащшем случае класс Shelter работает напрямую с классом Dog. Например, мы расширили приют и
 * теперь в нём живут ещё и кошки. Мы создадим класс кошек и нам придётся вносить изменения в код класса
 * Shelter. А можно было бы изначально создать интерфейс Animals и унаследовать от него классы Dog и Cat.
 * В качестве поля вместо Map<Integer, Dog> использовать абстракцию Map<Integer, Animal>. Параметры методов
 * также заменить на Animal.
 */

public class Shelter {

    private Map<Integer, Dog> dogs = new HashMap<>();
    private static int id = 1;

    public Shelter(Map<Integer, Dog> dogs) {
        this.dogs = dogs;
    }

    public void addDog(Dog dog) {
        System.out.println("В приюте новая собачка...");
        dog.setId(id++);
        dogs.put(dog.getId(), dog);
    }

    public void foundTheOwners(Dog dog) {
        System.out.println("Собачка нашла хозяев");
        dogs.remove(dog.getId());
    }
}
