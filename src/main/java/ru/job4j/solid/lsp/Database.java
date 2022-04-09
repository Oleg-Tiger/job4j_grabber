package ru.job4j.solid.lsp;

/**
 * Данный класс описывает модель БД и содержит в себе методы подключения к БД,
 * записи и чтения данных и объединения таблиц
 */
public class Database {
    void connect() {
        System.out.println("Метод создаёт подключение к БД");
    }
    void read() {
        System.out.println("Читаем данные из БД");
    }

    void write() {
        System.out.println("Записываем данные в БД");
    }

    void joinTables() {
        System.out.println("Метод объединяет таблицы");
    }
}
