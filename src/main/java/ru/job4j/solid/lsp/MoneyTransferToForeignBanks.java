package ru.job4j.solid.lsp;

/**
 * Добавили функционал для переводов в иностранные банки. Минимальная сумма перевода - 100 рублей.
 * Наследование от класса MoneyTransfer - нарушение принципа LSP, хоть денежные переводы в иностранные
 * банки и являются разновидностью переводов. Тут ужесточается предусловие.
 */

public class MoneyTransferToForeignBanks extends MoneyTransfer {

    @Override
    void sendMoney(double sum) {
        if (sum < 100) {
            throw new IllegalArgumentException("Введённая сумма должна быть не менее 100 рублей");
        }
    }
}
