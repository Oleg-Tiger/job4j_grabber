package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportForAccountant implements Report {

    private Store store;
    private Converter converter;

    public ReportForAccountant(Store store, Converter converter) {
        this.store = store;
        this.converter = converter;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return new ReportEngine(this.store, this.converter).generate(filter);
    }
}
