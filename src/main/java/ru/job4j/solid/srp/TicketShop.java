package ru.job4j.solid.srp;

import java.util.ArrayList;
import java.util.List;

/**
 * Данный класс - магазин билетов, реализация интерфейса Shop. Для покупки пользователь
 * указывает имя и id. Покупка регистрируется в системе. Для сбоора статистики магазин
 * запоминает всех пользователей совершивших покупки и хранит их в списке. Допустим,
 * нам теперь требуется просто запоминать всех пользователей, совершавших покупки, без
 * сохранения порядка, в котором они были совершены, и без повторов. В этом случае логичнее
 * использовать Set. Но в нашем случае класс сам создаёт и инициализирует объект.
 * То есть при создании объекта - нет возможности заменить List на Set. Это можно исправить,
 * заменив тип поля buyers на Collection, и инициализировать это поле в конструкторе так, как необходимо.
 */

public class TicketShop implements Shop {
   List<Buyer> buyers;

    public TicketShop() {
        buyers = new ArrayList<>();
    }

    public List<Buyer> getBuyers() {
        return buyers;
    }

    @Override
    public void buy(String name, int id) {
        Buyer buyer = new Buyer(name, id);
        purchaseRegistrationInTheSystem(buyer);
        buyers.add(buyer);
    }

    private static void purchaseRegistrationInTheSystem(Buyer buyer) {
        System.out.printf("Регистрация покупки в системе на имя %s, %s", buyer.getName(), buyer.getId());
    }
}
