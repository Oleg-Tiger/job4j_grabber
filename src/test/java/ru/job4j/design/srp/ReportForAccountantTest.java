package ru.job4j.design.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import java.util.Calendar;

public class ReportForAccountantTest {

    @Test
    public void whenReportInDollar() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee ivan = new Employee("Ivan", now, now, 100000);
        store.add(ivan);
        Report accountant = new ReportForAccountant(store, new ConverterToDollar());
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(ivan.getName()).append(";")
                .append(ivan.getHired()).append(";")
                .append(ivan.getFired()).append(";")
                .append(ivan.getSalary() * 0.01159).append(";")
                .append(System.lineSeparator());
        assertThat(accountant.generate(em -> true), is(expect.toString()));
    }
}