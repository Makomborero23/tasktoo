import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.Scanner;

public class xmlreader{
    public static void main(String[] args) {
        try {
            File inputFile = new File("C:/Users/Makomborero/Desktop/data.xml");//input file
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();//creating a document builder factory
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();//creating a document builder
            Document doc = dBuilder.parse(inputFile);//parsing the input file
            doc.getDocumentElement().normalize();//normalizing the document

            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());//printing the root element
            System.out.println("----------------------------");

            NodeList nList = doc.getElementsByTagName("*");
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Enter field name to retrieve (or type 'exit' to quit): ");
                String fieldName = scanner.nextLine();
                if (fieldName.equalsIgnoreCase("exit")) {
                    break;
                }
                boolean found = false;
                for (int temp = 0; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        if (eElement.getNodeName().equals(fieldName)) {
                            System.out.println("Field name: " + fieldName);
                            System.out.println("Field value: " + eElement.getTextContent());
                            System.out.println("----------------------------");
                            found = true;
                            break;
                        }
                    }
                }
                if (!found) {
                    System.out.println("Field '" + fieldName + "' not found.");
                }
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

}
