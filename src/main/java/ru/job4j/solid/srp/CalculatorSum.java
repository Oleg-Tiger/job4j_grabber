package ru.job4j.solid.srp;

import java.util.Collection;

/**
 * Данный класс является реализацией интерфейса Calculator, он принимает в качестве аргумента
 * коллекцию Integer и считает сумму всех элементов коллекции. Но если нам понадобится, например,
 * посчитать произведение элементов, то придётся создавать новый класс, реализующий интерфейс
 * и копировать код. Мы можем передать в конструкторе данного класса BinaryOperator и создавать
 * объект класса в зависимости от того, какой результат нам нужен - сложение, умножение либо какая-то
 * ещё функция и подставить его в качестве аргумента в метод reduce().
 */

public class CalculatorSum implements Calculator<Integer> {

    @Override
    public Integer calculate(Collection<Integer> collection) {
        return collection.stream()
                .reduce((s1, s2) -> s1 + s2)
                .orElse(0);
    }
}
