package ru.job4j.design.lsp.parking;

import java.util.*;

public class CarAndTruckParking implements Parking {
    private int truckPlaces;
    private int carPlaces;
    private final List<Transport> transports = new ArrayList<>();

    public CarAndTruckParking(int truckPlaces, int carPlaces) {
        this.truckPlaces = truckPlaces;
        this.carPlaces = carPlaces;
    }

    @Override
    public boolean addTransport(Transport transport) {
       return false;
    }

    @Override
    public void removeTransport(Transport transport) {

    }
}
