package ro.ubb.labproblems.Repository.XmlRepositories;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ro.ubb.labproblems.Domain.Student;
import ro.ubb.labproblems.Domain.Validators.Validator;
import ro.ubb.labproblems.Domain.Validators.ValidatorException;
import ro.ubb.labproblems.Repository.InMemoryRepositories.InMemoryRepository;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.util.*;

/**
 * @author Catalin
 */

public class StudentXMLRepository extends InMemoryRepository<Long, Student> {
    private String XMLfile;

    /**
     * Constructor for the StudentXMLRepository class
     * @param studentValidator Validator
     * @param XMLfile String
     */
    public StudentXMLRepository(Validator<Student> studentValidator,String XMLfile){
        super(studentValidator);
        this.XMLfile=XMLfile;
        try {
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a entity to the repository
     * @param entity must not be null.
     * @return Optional
     * @throws ValidatorException validator exception
     */
    public Optional<Student> addToRepo(Student entity) throws ValidatorException {
        Optional<Student> optional = super.addToRepo(entity);
        if (optional.isPresent()) {
            return optional;
        }
        try {
            saveToFile(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * Function used for appending text to child
     * @param document Document
     * @param parent Node
     * @param tagName String
     * @param textContent String
     */
    private static void appendChildWithText(Document document,
                                            Node parent, String tagName, String textContent) {
        Element element = document.createElement(tagName);
        element.setTextContent(textContent);
        parent.appendChild(element);
    }

    /**
     * Function that saves a entity in the XML file
     * @param entity Student
     * @throws Exception an exception
     */
    private void saveToFile(Student entity) throws Exception{
        Document document = DocumentBuilderFactory
                .newInstance()
                .newDocumentBuilder()
                .parse(XMLfile);
        Element root = document.getDocumentElement();
        Element studentElement = document.createElement("student");
        studentElement.setAttribute("serialNumber",entity.getSerialNumber());
        root.appendChild(studentElement);

        appendChildWithText(document, studentElement, "name", entity.getName());
        appendChildWithText(document, studentElement, "group", String.valueOf(entity.getGroup()));
        appendChildWithText(document, studentElement, "id", entity.getId().toString());

        Transformer transformer =
                TransformerFactory.newInstance().newTransformer();
        transformer.transform(new DOMSource(root),
                new StreamResult(new FileOutputStream(
                        XMLfile)));
    }

    /**
     * Loads tha data from the XML file in the memory
     * @throws Exception an Exception
     */
    private void loadData() throws Exception {
        List<Student> listOfStudents = new ArrayList<>();
        DocumentBuilderFactory documentBuilderFactory =
                DocumentBuilderFactory.newInstance();

        DocumentBuilder documentBuilder =
                documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(XMLfile);
        Element root = document.getDocumentElement();

        NodeList nodes = root.getChildNodes();
        int len = nodes.getLength();
        for (int i = 0; i < len; i++) {
            Node studentNode = nodes.item(i);
            if (studentNode instanceof Element) {
                Student b = createStudent((Element) studentNode);
                listOfStudents.add(b);
            }
        }
        Map<Long, Student> map= new HashMap<>();
        for (Student s : listOfStudents){
            map.putIfAbsent(s.getId(),s);
        }
        this.setEntities(map);
    }

    /**
     * Creates a Student entity from the XML file
     * @param studentNode Element
     * @return Student
     */
    private Student createStudent(Element studentNode) {
        String serialNumber = studentNode.getAttribute("serialNumber");
        Student b = new Student();
        b.setSerialNumber(serialNumber);

        b.setName(getTextFromTagName(studentNode, "name"));
        b.setGroup(Integer.valueOf(getTextFromTagName(studentNode, "group")));
        b.setId(Long.valueOf(getTextFromTagName(studentNode, "id")));

        return b;
    }

    /**
     * Returns the text from a tag name
     * @param element the element
     * @param tagName the tag name
     * @return String
     */
    private String getTextFromTagName(Element element, String tagName) {
        NodeList elements = element.getElementsByTagName(tagName);
        Node node = elements.item(0);
        return node.getTextContent();
    }


}
