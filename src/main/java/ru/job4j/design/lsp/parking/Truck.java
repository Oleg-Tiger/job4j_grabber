package ru.job4j.design.lsp.parking;

import java.util.Objects;

public class Truck implements Transport {
    private final String number;
    private final int size;


    public Truck(String number, int size) {
        this.number = number;
        this.size = size;
    }


    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Truck truck = (Truck) o;
        return Objects.equals(number, truck.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
