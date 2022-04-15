package ru.job4j.design.lsp;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Predicate;

public interface FoodStorage {

    boolean add(Food food);
    Predicate<Food> getCondition();
    List<Food> getProducts();
    default double getPercentLifeTimePassed(Food food) {
        long lifeTime = ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
        long lifeTimePassed = ChronoUnit.DAYS.between(food.getCreateDate(), LocalDate.now());
        return (lifeTimePassed + 1) * 100.0 / (lifeTime + 1);
    }
}

