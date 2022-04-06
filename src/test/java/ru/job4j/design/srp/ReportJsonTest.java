package ru.job4j.design.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ReportJsonTest {

    @Test
    public void whenReportJson() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss X");
        String dateFormat = formatter.format(now.getTime());
        Employee worker = new Employee("Ivan", now, now, 100000);
        store.add(worker);
        Report json = new ReportJson(store);
        String expect = "[{\"name\":\"Ivan\","
                + "\"hired\":\"" + dateFormat + "\",\"fired\":\"" + dateFormat + "\",\"salary\":100000.0}]";
        assertThat(json.generate(em -> true), is(expect));
    }
}