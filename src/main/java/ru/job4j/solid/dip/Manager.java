package ru.job4j.solid.dip;

import java.util.List;

/**
 * Объект класса Manager имеет работников Worker в подчинении.  Есть метод управления, с помощью которого
 * менеджер даёт задание работнику. Имеется прямая зависимость класса Manager от Worker.
 * Допустим, нам нужно с помощью этих классов описать логику работы ресторана. У нас есть
 * бармены и официанты, которые относятся к работникам и подчиняются менеджеру. Так же есть повара,
 * которыми руководит Шеф-повар. Правильнее было бы создать слудеющую структуру:
 * 1. Интерфейс Worker - его подинтерфейсы RestaurantWorker(работники зала - официанты и бармены) и
 * KitchenWorker - повара,
 * 2. Интерфейс Manager(управляющий) и его реализации - RestaurantManager c полем List<RestaurantWorker>
 * и KitchenManager c полем List<KitchenWorker>
 * 3. Класс Director с полем Manager...
 */
public class Manager {
    private List<Worker> workers;

    public void manage(Worker worker) {
        // some logic...
        worker.work();
    }

}
