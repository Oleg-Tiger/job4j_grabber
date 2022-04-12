package ru.job4j.design.lsp;

import java.util.List;
import java.util.function.Predicate;

public interface FoodStorage {

    void add(Food food);
    Predicate<Food> getCondition();
    List<Food> getProducts();
}
