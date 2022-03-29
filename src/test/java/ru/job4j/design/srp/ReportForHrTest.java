package ru.job4j.design.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import java.util.Calendar;

public class ReportForHrTest {

    @Test
    public void whenReportForHr() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee ivan = new Employee("Ivan", now, now, 100000);
        Employee oleg = new Employee("Oleg", now, now, 200000);
        Employee alex = new Employee("Alex", now, now, 150000);
        store.add(ivan);
        store.add(oleg);
        store.add(alex);
        Report hr = new ReportForHr(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(oleg.getName()).append(";")
                .append(oleg.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(alex.getName()).append(";")
                .append(alex.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(ivan.getName()).append(";")
                .append(ivan.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(hr.generate(em -> true), is(expect.toString()));
    }
}