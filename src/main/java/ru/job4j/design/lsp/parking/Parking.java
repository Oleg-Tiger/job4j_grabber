package ru.job4j.design.lsp.parking;

public interface Parking {
    boolean addTransport(Transport transport);
    void removeTransport(Transport transport);
}
