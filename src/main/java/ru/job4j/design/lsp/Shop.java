package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Shop implements FoodStorage {

    private int discount;
    private final List<Food> products = new ArrayList<>();
    private final Predicate<Food> condition = food -> getPercentLifeTimePassed(food) >= 25
            && getPercentLifeTimePassed(food) <= 100;

    public Shop(int discount) {
        this.discount = discount;
    }

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (condition.test(food)) {
            if (getPercentLifeTimePassed(food) > 75) {
                food.setDiscount(discount);
            }
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

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public static void setFoodPriceAndDiscount(Food.Price price, int discount) {
        price.setDiscount(discount);
        if (discount == 0) {
            price.setPriceWithDiscount(price.getPrice());
        } else {
            int a = (int) (price.getPrice() * (100 - discount));
            price.setPriceWithDiscount(a * 0.01);
        }
    }
}
