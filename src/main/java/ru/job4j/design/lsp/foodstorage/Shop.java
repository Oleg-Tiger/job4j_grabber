package ru.job4j.design.lsp.foodstorage;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    @Override
    public boolean remove(Food food) {
        if (!condition.test(food)) {
            return products.remove(food);
        }
        if (getPercentLifeTimePassed(food) > 75) {
            food.setDiscount(discount);
        }
        return false;
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
            double a = (price.getPrice() * (100 - discount) * 0.01);
            BigDecimal result = new BigDecimal(a);
            price.setPriceWithDiscount(result.setScale(2, RoundingMode.DOWN).doubleValue());
        }
    }
}
