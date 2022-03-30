package ru.job4j.design.srp;

public class ConverterToDollar implements Converter {

    final static double COURSE = 0.01159;

    @Override
    public double convert(double salary) {
        return salary *  COURSE;
    }
}
