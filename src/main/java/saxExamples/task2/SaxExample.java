package saxExamples.task2;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;

public class SaxExample {
    private static ArrayList<Employee> employees = new ArrayList<>();

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        // Загружаем файл из ресурсов как InputStream
        ClassLoader classloader = SaxExample.class.getClassLoader();
        var file = classloader.getResourceAsStream("SAX/Task2/task2.xml");

        AdvancedXMLHandler handler = new AdvancedXMLHandler(employees);
        parser.parse(file, handler);

        for (Employee employee : employees)
            System.out.println(String.format("Имя сотрудника: %s, его должность: %s", employee.getName(), employee.getJob()));
    }
}
