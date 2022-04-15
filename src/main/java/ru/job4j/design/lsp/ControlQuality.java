package ru.job4j.design.lsp;

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
}


