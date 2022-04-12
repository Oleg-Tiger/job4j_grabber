package ru.job4j.design.lsp;

import java.util.List;

public class ControlQuality {

    private final List<FoodStorage> storages;

    public ControlQuality(List<FoodStorage> storages) {
        this.storages = storages;
    }

    public void addToStorage(Food food) {
        FoodStorage storage = checkCondition(food);
        if (storage == null) {
           throw new IllegalArgumentException("Подходящее хранилище не найдено");
        }
        storage.add(food);
    }

    private FoodStorage checkCondition(Food food) {
        FoodStorage rsl = null;
        for (FoodStorage storage : storages) {
           if (storage.getCondition().test(food)) {
                rsl = storage;
                break;
            }
        }
        return rsl;
        }

    public List<FoodStorage> getStorages() {
        return storages;
    }
}


