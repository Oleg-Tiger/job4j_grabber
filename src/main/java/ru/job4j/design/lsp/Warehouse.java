package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Warehouse implements FoodStorage {

    private final List<Food> products = new ArrayList<>();
    private final Predicate<Food> condition = food -> food.getPercentLifeTimePassed() < 25;


    @Override
    public void add(Food food) {
        products.add(food);
    }

    public List<Food> getProducts() {
        return products;
    }

    public Predicate<Food> getCondition() {
        return condition;
    }
}
