package saxExamples.task1;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

class XMLHandler extends DefaultHandler {
    private ArrayList<Employee> employees;

    public XMLHandler(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("employee")) {
            String name = attributes.getValue("name");
            String job = attributes.getValue("job");
            employees.add(new Employee(name, job));
        }
    }
}
