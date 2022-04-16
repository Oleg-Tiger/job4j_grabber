package ru.job4j.solid.lsp;

/**
 * Также является наследником Database, но поскольку данная БД не является реляционной, в ней нет таблиц,
 * соответственно функционал объединения таблиц не будет реализован. В случае, если мы сначала работаем
 * с PostgreSql, затем заменяли её на MongoSql, мы получим исключение.
 * Правильное решение в данном случае - унаследовать от Database классы Sql и NoSql, и уже в них добавить
 * функционал, характерный для реляционных и нереляционных БД.
 *
 * Этот класс является также примером нарушения принципа ISP. Мы неправильно выделили абстракции - то есть
 * вместо интерфейсов Sql и NoSql выделили один общий Database и добавили в него все возможные методы, и,
 * как следствие - в классе MongoDatabase нужно реализовывать метод joinTables(), хоть данная БД и не имеет
 * такого функционала.
 */
public class MongoDatabase extends Database {
    @Override
    void connect() {
        super.connect();
    }

    @Override
    void read() {
        super.read();
    }

    @Override
    void write() {
        super.write();
    }

    @Override
    void joinTables() {
        throw new IllegalArgumentException("В Mongo нет таблиц, это не реляционная БД");
    }
}
