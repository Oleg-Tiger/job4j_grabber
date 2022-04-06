package ru.job4j.design.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.function.Predicate;

public class ReportJson implements Report {
    private Store store;

    public ReportJson(Store store) {
        this.store = store;
    }

    public String generate(Predicate<Employee> filter) {
        GsonBuilder builder = new GsonBuilder().registerTypeAdapter(Calendar.class, new CalendarInJson())
                .registerTypeAdapter(GregorianCalendar.class, new CalendarInJson());
        Gson lib = builder.create();
        return lib.toJson(store.findBy(filter));
    }
}
