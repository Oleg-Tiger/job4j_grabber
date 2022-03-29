package ru.job4j.design.srp;

public class ConverterToRoubles implements Converter {
    @Override
    public double convert(double salary) {
        return salary;
    }
}
