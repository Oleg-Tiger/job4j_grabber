package ru.job4j.design.srp;

public class ConverterToDollar implements Converter {

    @Override
    public double convert(double salary) {
        return salary * 0.01159;
    }
}
