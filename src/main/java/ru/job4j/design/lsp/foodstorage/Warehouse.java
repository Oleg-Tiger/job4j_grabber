package ru.job4j.design.lsp.foodstorage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Warehouse implements FoodStorage {

    private final List<Food> products = new ArrayList<>();
    private final Predicate<Food> condition = food -> getPercentLifeTimePassed(food) < 25;

    @Override
    public boolean add(Food food) {
      boolean rsl = false;
       if (condition.test(food)) {
           rsl = products.add(food);
       }
       return rsl;
    }

    @Override
    public boolean remove(Food food) {
        if (!condition.test(food)) {
            return products.remove(food);
        }
        return false;
    }

    public List<Food> getProducts() {
        return List.copyOf(products);
    }

    public Predicate<Food> getCondition() {
        return condition;
    }
}
