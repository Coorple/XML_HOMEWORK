package Homework;

import java.io.*;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class HomeWork
{
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("Homework/Message.xml"),
                        "UTF-8"
                )
        );
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream("Homework/Message2.xml"),
                        "UTF-8"
                )
        );

        String line = null;

        while((line = reader.readLine()) != null) {
            writer.write(line);
        }

        reader.close();
        writer.close();

        boolean answer = validateXMLSchema("Homework/Message.xsd", "Homework/Message.xml");
        System.out.println("Result:" + answer);
    }

    public static boolean validateXMLSchema(String xsdPath, String xmlPath)
    {
        try {
            // Получить фабрику для схемы
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            // Загружаем схему из ресурсов как InputStream
            ClassLoader classloader = xsdValidationExample.XsdValidator.class.getClassLoader();
            var file = classloader.getResourceAsStream(xsdPath);

            // Загрузить схему из XSD
            Schema schema = factory.newSchema(new StreamSource(file));

            // Создать валидатор (проверяльщик)
            Validator validator = schema.newValidator();

            // Загружаем xml из ресурсов как InputStream
            file = classloader.getResourceAsStream(xmlPath);

            // Запусить проверку - если будет исключение, значит есть ошибки.
            // Если нет - все заполнено правильно
            validator.validate(new StreamSource(file));
        } catch (IOException | SAXException e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }
        return true;
    }
}
