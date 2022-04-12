package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Shop implements FoodStorage {

    private int discount;
    private final List<Food> products = new ArrayList<>();
    private final Predicate<Food> condition = food -> food.getPercentLifeTimePassed() >= 25
            && food.getPercentLifeTimePassed() <= 100;

    public Shop(int discount) {
        this.discount = discount;
    }

    @Override
    public void add(Food food) {
        if (food.getPercentLifeTimePassed() > 75) {
            food.setDiscount(discount);
        }
        products.add(food);
    }

    public List<Food> getProducts() {
        return products;
    }

    public Predicate<Food> getCondition() {
        return condition;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
