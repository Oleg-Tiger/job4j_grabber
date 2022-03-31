package ru.job4j.design.srp;

import com.google.gson.GsonBuilder;
import java.util.function.Predicate;

public class ReportJson implements Report {
    private Store store;

    public ReportJson(Store store) {
        this.store = store;
    }

    public String generate(Predicate<Employee> filter) {
        var lib = new GsonBuilder().create();
        return lib.toJson(store.findBy(filter));
    }
}
