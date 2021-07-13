package saxExamples.task2;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

class AdvancedXMLHandler extends DefaultHandler {
    private String name, job, lastElementName;
    private ArrayList<Employee> employees;

    public AdvancedXMLHandler(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        lastElementName = qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String information = new String(ch, start, length);

        information = information.replace("\n", "").trim();

        if (!information.isEmpty()) {
            if (lastElementName.equals("name"))
                name = information;
            if (lastElementName.equals("job"))
                job = information;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ( (name != null && !name.isEmpty()) && (job != null && !job.isEmpty()) ) {
            employees.add(new Employee(name, job));
            name = null;
            job = null;
        }
    }
}
