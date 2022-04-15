package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Trash implements FoodStorage {

    private final List<Food> products = new ArrayList<>();
    private final Predicate<Food> condition = food -> getPercentLifeTimePassed(food) > 100;

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (condition.test(food)) {
            rsl = products.add(food);
        }
        return rsl;
    }

    public List<Food> getProducts() {
        return List.copyOf(products);
    }

    public Predicate<Food> getCondition() {
        return condition;
    }
}
