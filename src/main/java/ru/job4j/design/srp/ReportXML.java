package ru.job4j.design.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.function.Predicate;


public class ReportXML implements Report {
    private Store store;

    public ReportXML(Store store) {
        this.store = store;
    }

    public String generate(Predicate<Employee> filter) throws JAXBException {
        var employees = store.findBy(filter);
        JAXBContext context = JAXBContext.newInstance(Employee.Employees.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(new Employee.Employees(employees), writer);
            xml = writer.getBuffer().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xml;
    }
}
