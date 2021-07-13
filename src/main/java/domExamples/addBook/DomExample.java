package domExamples.addBook;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;

public class DomExample {

    public static void main(String[] args) {
        try {
            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            // Загружаем файл из ресурсов как InputStream
            ClassLoader classloader = domExamples.task1.DomExample.class.getClassLoader();
            var file = classloader.getResourceAsStream("DOM/AddBook/BookCatalog.xml");

            // Создается дерево DOM документа из файла
            Document document = documentBuilder.parse(file);

            // Вызываем метод для добавления новой книги
            addNewBook(document);

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    // Функция добавления новой книги и записи результата в файл
    private static void addNewBook(Document document) throws TransformerFactoryConfigurationError, DOMException {
        // Получаем корневой элемент
        Node root = document.getDocumentElement();

        // Создаем новую книгу по элементам
        // Сама книга <Book>
        Element book = document.createElement("Book");
        // <Title>
        Element title = document.createElement("Title");
        // Устанавливаем значение текста внутри тега
        title.setTextContent("Incredible book about XML");
        // <Author>
        Element author = document.createElement("Author");
        author.setTextContent("Vetoshkin Andrey");
        // <Date>
        Element date = document.createElement("Date");
        date.setTextContent("2021");
        // <ISBN>
        Element isbn = document.createElement("ISBN");
        isbn.setTextContent("0-06-999999-9");
        // <Publisher>
        Element publisher = document.createElement("Publisher");
        publisher.setTextContent("SDET School");
        // <Cost>
        Element cost = document.createElement("Cost");
        cost.setTextContent("499");
        // Устанавливаем атрибут
        cost.setAttribute("currency", "RUB");

        // Добавляем внутренние элементы книги в элемент <Book>
        book.appendChild(title);
        book.appendChild(author);
        book.appendChild(date);
        book.appendChild(isbn);
        book.appendChild(publisher);
        book.appendChild(cost);
        // Добавляем книгу в корневой элемент
        root.appendChild(book);

        // Записываем XML в файл
        writeDocument(document);
    }

    // Функция для сохранения DOM в файл
    private static void writeDocument(Document document) throws TransformerFactoryConfigurationError {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream("src/main/resources/DOM/AddBook/other.xml");
            StreamResult result = new StreamResult(fos);
//            tr.setOutputProperty(OutputKeys.INDENT, "yes");
//            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            tr.transform(source, result);
        } catch (TransformerException | IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
