package ru.job4j.solid.lsp;

/**
 * Также является наследником Database, но поскольку данная БД не является реляционной, в ней нет таблиц,
 * соответственно функционал объединения таблиц не будет реализован. В случае, если мы сначала работаем
 * с PostgreSql, затем заменяли её на MongoSql, мы получим исключение.
 * Правильное решение в данном случае - унаследовать от Database классы Sql и NoSql, и уже в них добавить
 * функционал, характерный для реляционных и нереляционных БД.
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
