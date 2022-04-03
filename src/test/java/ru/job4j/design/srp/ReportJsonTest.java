package ru.job4j.design.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import java.util.Calendar;


public class ReportJsonTest {

    @Test
    public void whenReportJson() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH);
        int day = now.get(Calendar.DAY_OF_MONTH);
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int min = now.get(Calendar.MINUTE);
        int sec = now.get(Calendar.SECOND);
        Employee worker = new Employee("Ivan", now, now, 100000);
        store.add(worker);
        Report json = new ReportJson(store);
        String expect = "[{\"name\":\"Ivan\","
                + "\"hired\":{\"year\":" + year + ",\"month\":" + month + ",\"dayOfMonth\":" + day
                + ",\"hourOfDay\":" + hour + ",\"minute\":" + min + ",\"second\":" + sec
                + "},\"fired\":{\"year\":" + year + ",\"month\":" + month + ",\"dayOfMonth\":" + day
                + ",\"hourOfDay\":" + hour + ",\"minute\":" + min + ",\"second\":" + sec
                + "},\"salary\":100000.0}]";
        assertThat(json.generate(em -> true), is(expect));
    }
}