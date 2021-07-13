package saxExamples.task3;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SaxExample {
    private static boolean isFound;

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        // Загружаем файл из ресурсов как InputStream
        ClassLoader classloader = SaxExample.class.getClassLoader();
        var file = classloader.getResourceAsStream("SAX/Task3/task3.xml");

        SearchingXMLHandler handler = new SearchingXMLHandler("root");
        parser.parse(file, handler);

        if (!isFound)
            System.out.println("Элемент не был найден.");
    }

    private static class SearchingXMLHandler extends DefaultHandler {
        private String element;
        private boolean isEntered;

        public SearchingXMLHandler(String element) {
            this.element = element;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (isEntered) {
                System.out.println(String.format("Найден элемент <%s>, его атрибуты:", qName));

                int length = attributes.getLength();
                for(int i = 0; i < length; i++)
                    System.out.println(String.format("Имя атрибута: %s, его значение: %s", attributes.getQName(i), attributes.getValue(i)));
            }

            if (qName.equals(element)) {
                isEntered = true;
                isFound = true;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equals(element))
                isEntered = false;
        }
    }
}
