package ru.job4j.design.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.util.Calendar;

public class ReportForProgrammerTest {

    @Test
    public void whenReportInHtml() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee ivan = new Employee("Ivan", now, now, 100000);
        store.add(ivan);
        Report programmer = new ReportForProgrammer(store, new ConvertToHtml());
        StringBuilder expect = new StringBuilder()
                .append("<html>").append(System.lineSeparator())
                .append("<head>").append(System.lineSeparator())
                .append("</head>").append(System.lineSeparator())
                .append("<body>").append(System.lineSeparator())
                .append("<div>").append(System.lineSeparator())
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(ivan.getName()).append(";")
                .append(ivan.getHired()).append(";")
                .append(ivan.getFired()).append(";")
                .append(ivan.getSalary()).append(";")
                .append(System.lineSeparator())
                .append("</div>").append(System.lineSeparator())
                .append("</body>").append(System.lineSeparator())
                .append("</html>").append(System.lineSeparator());
       assertThat(programmer.generate(em -> true), is(expect.toString()));
    }
}