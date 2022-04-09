package ru.job4j.solid.lsp;

/**
 * Наследник класса Database, переопределяем методы, добавляя специфическое поведение, если это требуется
 */
public class PostgreSqlDatabase extends Database {
    @Override
    void connect() {
        super.connect();
    }

    @Override
    void read() {
        System.out.println();
        super.read();
    }

    @Override
    void write() {
        System.out.println();
        super.write();
    }

    @Override
    void joinTables() {
        System.out.println();
        super.joinTables();
    }
}
