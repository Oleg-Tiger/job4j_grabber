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
        int size = transport.getSize();
        if (size == 1) {
           return addCarOnCarPlace(transport);
        }
        if (addTruckOnTruckPlace(transport)) {
           return true;
       }
        Optional<Transport> smallerTruck = checkTruckWithSmallerSize(size);
        if (smallerTruck.isPresent()) {
            Transport smaller = smallerTruck.get();
            truckPlaces++;
            carPlaces -= smaller.getSize();
            return addTruckOnTruckPlace(transport);
        }
         return addTruckOnCarPlaces(transport);
    }

    @Override
    public boolean removeTransport(Transport transport) {
        boolean rsl = transports.remove(transport);
        if (!rsl) {
            throw new IllegalArgumentException("Такой машины на парковке нет!!!");
        }
        if (transport.getSize() == Car.SIZE) {
            carPlaces++;
        } else {
            truckPlaces += transport.getSize();
        }
        return true;
    }

    private boolean addCarOnCarPlace(Transport transport) {
        if (carPlaces > 0) {
            carPlaces--;
            return transports.add(transport);
        }
        return false;
    }

    private boolean addTruckOnTruckPlace(Transport transport) {
        if (truckPlaces > 0) {
            truckPlaces--;
            return transports.add(transport);
        }
        return false;
    }

    private boolean addTruckOnCarPlaces(Transport transport) {
        int size = transport.getSize();
        if (size <= carPlaces) {
            carPlaces -= size;
            return transports.add(transport);
        }
        return false;
    }

    private Optional<Transport> checkTruckWithSmallerSize(int size) {
        transports.sort(Comparator.comparing(Transport::getSize));
        return transports.stream()
                .filter(tr -> tr.getSize() > 1 && tr.getSize() < size && tr.getSize() <= carPlaces)
                .findFirst();
    }

    public int getFreeTruckPlaces() {
        return truckPlaces;
    }

    public int getFreeCarPlaces() {
        return carPlaces;
    }

    public List<Transport> getTransports() {
        return List.copyOf(transports);
    }
}
