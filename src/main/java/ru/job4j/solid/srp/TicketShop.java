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
 *
 * Допустим, со временем мы решили внедрить функцию отправки СМС пользователю с подтверждением заказа.
 * Мы могли бы добавить необходимый для этого метод sendSMS() и изменить метод buy(). Но тут уже идёт нарушение
 * принципа OCP. Чтобы не нарушать его - вместо изменений надо сделать расширение. Создать класс - наследник TicketShop
 * и добавить и переопределить в нём необходимые методы. Или, как вариант создать новый класс, реализующий интерфейс
 * Shop.
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
        sendSms(id);

    }

    private void sendSms(int id) {
        System.out.println("Отправляем SMS пользователю с информацией по заказу");
    }

    private static void purchaseRegistrationInTheSystem(Buyer buyer) {
        System.out.printf("Регистрация покупки в системе на имя %s, %s", buyer.getName(), buyer.getId());
    }
}
