package ru.job4j.solid.dip;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Данный класс предоставляет нам возможность поиска в хранилище по условию. Нарушение принципа Dip в том,
 * что данный класс - сервис поиска зависит хранилища PersonListStorage. То есть в случае, если в дальнейшем
 * мы будем хранить объекты Person в другом хранилище(Например, база данных), то мы уже не сможем воспользолваться
 * данным классом.
 * Думаю, что оптимально было бы:
 * 1. Создать интерфейс Storage с методами add(), remove() и т. д.
 * 2. Вместо класса FindBy Predicate создать интерфейс.
 * 3. В реализациях хранилища реализовать интерфейс Storage(В каждом хранилище своя логика добавления и удаления).
 * 4. В реализациях хранилища реализовать интерфейс FindByPredicate(В каждом хранилище своя логика поиска) .
 */
public class FindByPredicate {

    public List<Person> findAll(PersonListStorage storage, Predicate<Person> predicate) {
        return new ArrayList();
    }

}
