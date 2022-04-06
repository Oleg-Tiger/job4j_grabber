package ru.job4j.design.srp;

import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import static org.junit.Assert.assertTrue;


public class ReportXMLTest {

    @Before
    public void setUp() {
        XMLUnit.setIgnoreComments(true);
        XMLUnit.setIgnoreWhitespace(true);
    }

    @Test
    public void whenReportXML() throws IOException, SAXException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss X");
        String dateFormat = formatter.format(now.getTime());
        Employee worker = new Employee("Ivan", now, now, 100000);
        store.add(worker);
        Report xml = new ReportXML(store);
        String result = xml.generate(em -> true);
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
                + "<employees><employee name=\"Ivan\">"
                + "<hired>" + dateFormat + "</hired>"
                + "<fired>" + dateFormat + "</fired>"
                + "<salary>100000.0</salary>"
                + "</employee></employees>";
        Diff diff = new Diff(expected, result);
        assertTrue(diff.similar());
    }
}