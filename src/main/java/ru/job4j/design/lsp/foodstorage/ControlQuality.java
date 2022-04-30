package ru.job4j.design.lsp.foodstorage;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {

    private final List<FoodStorage> storages;

    public ControlQuality(List<FoodStorage> storages) {
        this.storages = storages;
    }

    public void addToStorage(Food food) {
        FoodStorage storage = null;
        for (FoodStorage fs : storages) {
            if (fs.getCondition().test(food)) {
                storage = fs;
                break;
            }
        }
        if (storage == null) {
           throw new IllegalArgumentException("Подходящее хранилище не найдено");
        }
        storage.add(food);
    }

    public void resort() {
        List<Food> products = new ArrayList<>();
        for (FoodStorage storage : storages) {
            for (Food food : storage.getProducts()) {
                if (storage.remove(food)) {
                    products.add(food);
                }
            }
        }
        products.forEach(this::addToStorage);
    }
}


