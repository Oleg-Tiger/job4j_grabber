package ru.job4j.design.srp;

import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import static org.junit.Assert.assertTrue;


public class ReportXMLTest {

    @Before
    public void setUp() {
        XMLUnit.setIgnoreComments(true);
        XMLUnit.setIgnoreWhitespace(true);
    }

    @Test
    public void whenReportXML() throws JAXBException, IOException, SAXException {
        MemStore store = new MemStore();
        Calendar date = new GregorianCalendar();
        date.set(2022, 02, 31, 23, 23, 23);
        date.set(Calendar.MILLISECOND, 0);
        Employee worker = new Employee("Ivan", date, date, 100000);
        store.add(worker);
        Report xml = new ReportXML(store);
        String result = xml.generate(em -> true);
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
                + "<employees><employees name=\"Ivan\">"
                + "<hired>2022-03-31T23:23:23+03:00</hired>"
                + "<fired>2022-03-31T23:23:23+03:00</fired>"
                + "<salary>100000.0</salary>"
                + "</employees></employees>";
        Diff diff = new Diff(expected, result);
        assertTrue(diff.similar());
    }
}