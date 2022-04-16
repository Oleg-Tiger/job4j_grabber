package ru.job4j.design.lsp.parking;

import java.util.List;

public interface Parking {
    boolean addTransport(Transport transport);
    void removeTransport(Transport transport);
    List<Transport> getTransports();
}
