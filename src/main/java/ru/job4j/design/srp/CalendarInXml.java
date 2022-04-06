package ru.job4j.design.srp;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarInXml extends XmlAdapter<String, Calendar> {
    private static final DateFormat DATE_FORMAT
            =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss X");

    @Override
    public Calendar unmarshal(String s) {
        Calendar rsl = Calendar.getInstance();
        try {
            rsl.setTime(DATE_FORMAT.parse(s));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public String marshal(Calendar calendar) {
        return DATE_FORMAT.format(calendar.getTime());
    }
}
