package ru.job4j.design.srp;

import java.util.Comparator;

public class SalarySort implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return Double.compare(o1.getSalary(), o2.getSalary());
    }
}
