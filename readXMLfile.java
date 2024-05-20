import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.Scanner;

public class readXMLfile {
    public static void main(String[] args) {
        try {
            File inputFile = new File("data.xml");//input file

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            XMLHandler handler = new XMLHandler();

            saxParser.parse(inputFile, handler);

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Enter field name to retrieve (or type 'exit' to quit): ");
                String fieldName = scanner.nextLine();
                if (fieldName.equalsIgnoreCase("exit")) {
                    break;
                }

                String fieldValue = handler.getFieldValue(fieldName);
                if (fieldValue != null) {
                    System.out.println("Field name: " + fieldName);
                    System.out.println("Field value: " + fieldValue);
                    System.out.println("----------------------------");
                } else {
                    System.out.println("Field '" + fieldName + "' not found.");
                }
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class XMLHandler extends DefaultHandler {
        private String currentElement;
        private String currentValue;
        private boolean foundField;

        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            currentElement = qName;
            currentValue = "";
            foundField = false;
        }

        public void characters(char[] ch, int start, int length) throws SAXException {
            currentValue = new String(ch, start, length);
        }

        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (currentElement.equals(qName)) {
                foundField = true;
            }
        }

        public String getFieldValue(String fieldName) {
            if (foundField) {
                return currentValue;
            }
            return null;
        }
    }
}
