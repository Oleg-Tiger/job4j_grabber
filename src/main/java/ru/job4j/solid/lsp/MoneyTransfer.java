package ru.job4j.solid.lsp;

/**
 * Данный класс предназначен для перевода денег между счетами. Минимальная сумма - 10 рублей.
 */
public class MoneyTransfer {

    void sendMoney(double sum) {
     if (sum < 10) {
         throw new IllegalArgumentException("Введённая сумма должна быть не менее 10 рублей");
     }
    }
}
