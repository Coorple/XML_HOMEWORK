package domExamples.task3;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class DomExample {
    // Коллекция для хранения всех людей
    private static ArrayList<Person> people = new ArrayList<>();

    // Константы для элементов
    private static final String PROFESSOR = "professor";
    private static final String MEMBER = "member";
    private static final String STUDENT = "student";

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        // Получение фабрики, чтобы после получить билдер документов.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // Получили из фабрики билдер, который парсит XML, создает структуру Document в виде иерархического дерева.
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Загружаем файл из ресурсов как InputStream
        ClassLoader classloader = DomExample.class.getClassLoader();
        var file = classloader.getResourceAsStream("DOM/Task3/task3.xml");

        // Запарсили XML, создав структуру Document. Теперь у нас есть доступ ко всем элементам, каким нам нужно.
        Document document = builder.parse(file);

        // Получение информации про каждый элемент отдельно
        collectInformation(document, PROFESSOR);
        collectInformation(document, MEMBER);
        collectInformation(document, STUDENT);

        // Вывод информации
        people.forEach(System.out::println);
    }

    /**
     * Метод ищет информацию про теги по имени element и вносит всю информацию в коллекцию people.
     * @param document Документ, в котором будем искать элементы.
     * @param element Имя элемента, теги которого нужно найти. Должна быть одна из констант, которые определяются выше.
     */
    private static void collectInformation(Document document, final String element) {
        // Получение всех элементов по имени тега.
        NodeList elements = document.getElementsByTagName(element);

        // Перебор всех найденных элементов
        for (int i = 0; i < elements.getLength(); i++) {
            // Получение всех атрибутов элемента
            NamedNodeMap attributes = elements.item(i).getAttributes();
            String name = attributes.getNamedItem("name").getNodeValue();

            // В зависимости от типа элемента, нам нужно собрать свою дополнительну информацию про каждый подкласс, а после добавить нужные образцы в коллекцию.
            switch (element) {
                case PROFESSOR: {
                    String experience = attributes.getNamedItem("experience").getNodeValue();
                    String discipline = attributes.getNamedItem("discipline").getNodeValue();

                    people.add(new Professor(name, experience, discipline));
                } break;
                case STUDENT: {
                    String course = attributes.getNamedItem("course").getNodeValue();
                    String specialization = attributes.getNamedItem("specialization").getNodeValue();

                    people.add(new Student(name, course, specialization));
                } break;
                case MEMBER: {
                    String position = attributes.getNamedItem("position").getNodeValue();

                    people.add(new Member(name, position));
                } break;
            }
        }
    }
}
