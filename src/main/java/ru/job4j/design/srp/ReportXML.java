package ru.job4j.design.srp;

import ru.job4j.design.srp.Employee.Employees;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.function.Predicate;


public class ReportXML implements Report {
    private Store store;

    public ReportXML(Store store) {
        this.store = store;
    }

    public String generate(Predicate<Employee> filter) {
        Employees employees = new Employees(store.findBy(filter));
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            JAXBContext context = JAXBContext.newInstance(Employees.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(employees, writer);
            xml = writer.getBuffer().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xml;
    }
}
