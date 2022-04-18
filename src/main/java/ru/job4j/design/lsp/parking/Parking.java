package ru.job4j.design.lsp.parking;

import java.util.List;

public interface Parking {
    boolean addTransport(Transport transport);
    boolean removeTransport(Transport transport);
    List<Transport> getTransports();
}
