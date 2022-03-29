package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportForProgrammer implements Report {
    private TextConverter textConverter;
    private Store store;

    public ReportForProgrammer(Store store, TextConverter textConverter) {
        this.store = store;
        this.textConverter = textConverter;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return textConverter.convert(new ReportEngine(this.store, new ConverterToRoubles()).generate(filter));
    }
}
