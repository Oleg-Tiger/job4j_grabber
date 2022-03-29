package ru.job4j.design.srp;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class ReportForHr implements Report {

    private Store store;

    public ReportForHr(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;").append(System.lineSeparator());
        List<Employee> filteredEmployees = store.findBy(filter);
        Collections.sort(filteredEmployees, new SalarySort().reversed());
        for (Employee employee : filteredEmployees) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
