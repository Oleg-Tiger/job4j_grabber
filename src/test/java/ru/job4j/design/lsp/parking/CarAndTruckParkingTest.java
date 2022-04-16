package ru.job4j.design.lsp.parking;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Ignore;
import org.junit.Test;

public class CarAndTruckParkingTest {

    @Ignore
    @Test
    public void whenParkingSize1And2Then1TruckAnd2CarAdded() {
        Parking parking = new CarAndTruckParking(1, 2);
        assertTrue(parking.addTransport(new Car("a777aa77")));
        assertTrue(parking.addTransport(new Truck("a555aa77", 3)));
        assertFalse(parking.addTransport(new Truck("a333aa77", 2)));
        assertTrue(parking.addTransport(new Car("a666aa77")));
        assertFalse(parking.addTransport(new Car("a444aa77")));
    }

    @Ignore
    @Test
    public void whenParkingSize1And2Then2TruckAdded() {
        Parking parking = new CarAndTruckParking(1, 2);
        assertTrue(parking.addTransport(new Truck("a777aa77", 2)));
        assertTrue(parking.addTransport(new Truck("a555aa77", 3)));
        assertFalse(parking.addTransport(new Car("a444aa77")));
        assertFalse(parking.addTransport(new Truck("a333aa77", 2)));
    }

    @Ignore
    @Test
    public void whenParkingSize0And1Then1CarAddedAnd1CarRemovedAnd1CarAdded() {
        Parking parking = new CarAndTruckParking(0, 1);
        Transport car = new Car("a777aa77");
        Transport car2 = new Car("a444aa77");
        assertTrue(parking.addTransport(car));
        assertFalse(parking.addTransport(car2));
        parking.removeTransport(car);
        assertTrue(parking.addTransport(car2));
    }

    @Ignore
    @Test
    public void whenParkingSize1And0Then1TruckAddedAnd1TruckRemovedAnd1TruckAdded() {
        Parking parking = new CarAndTruckParking(1, 0);
        Transport truck = new Truck("a777aa77",9);
        Transport truck2 = new Truck("a444aa77",10);
        assertTrue(parking.addTransport(truck));
        assertFalse(parking.addTransport(truck2));
        parking.removeTransport(truck);
        assertTrue(parking.addTransport(truck2));
    }

    @Ignore
    @Test
    public void whenParkingSize0And3Then1TruckAddedAnd1TruckRemovedAnd1TruckAdded() {
        Parking parking = new CarAndTruckParking(0, 3);
        Transport truck = new Truck("a777aa77",3);
        Transport truck2 = new Truck("a444aa77",3);
        assertTrue(parking.addTransport(truck));
        assertFalse(parking.addTransport(truck2));
        parking.removeTransport(truck);
        assertTrue(parking.addTransport(truck2));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenRemovedCarNotAddedOnParking() {
        Parking parking = new CarAndTruckParking(0, 3);
        Transport truck = new Truck("a777aa77",3);
        Transport truck2 = new Truck("a444aa77",3);
        parking.addTransport(truck);
        parking.removeTransport(truck2);
    }
}