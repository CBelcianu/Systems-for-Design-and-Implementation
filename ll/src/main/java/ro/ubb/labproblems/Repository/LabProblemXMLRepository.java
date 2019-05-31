package ro.ubb.labproblems.Repository;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ro.ubb.labproblems.Domain.LabProblem;
import ro.ubb.labproblems.Domain.Validators.Validator;
import ro.ubb.labproblems.Domain.Validators.ValidatorException;

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
public class LabProblemXMLRepository extends InMemoryRepository<Long, LabProblem> {
    private String XMLfile;

    public LabProblemXMLRepository(Validator<LabProblem> labProblemValidator, String XMLfile){
        super(labProblemValidator);
        this.XMLfile=XMLfile;
        try {
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Optional<LabProblem> addToRepo(LabProblem entity) throws ValidatorException {
        Optional<LabProblem> optional = super.addToRepo(entity);
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

    private static void appendChildWithText(Document document,
                                            Node parent, String tagName, String textContent) {
        Element element = document.createElement(tagName);
        element.setTextContent(textContent);
        parent.appendChild(element);
    }

    public void saveToFile(LabProblem entity) throws Exception{
        Document document = DocumentBuilderFactory
                .newInstance()
                .newDocumentBuilder()
                .parse(XMLfile);
        Element root = document.getDocumentElement();
        Element labProblemElement = document.createElement("labProblem");
        labProblemElement.setAttribute("problemStatement",entity.getProblemStatement());
        root.appendChild(labProblemElement);

        appendChildWithText(document, labProblemElement, "teacher", entity.getTeacher());
        appendChildWithText(document, labProblemElement, "difficulty", String.valueOf(entity.getWeight()));
        appendChildWithText(document, labProblemElement, "dueDate", entity.getDueDate());
        appendChildWithText(document, labProblemElement, "subject", entity.getSubject());
        appendChildWithText(document, labProblemElement, "id", entity.getId().toString());

        Transformer transformer =
                TransformerFactory.newInstance().newTransformer();
        transformer.transform(new DOMSource(root),
                new StreamResult(new FileOutputStream(
                        XMLfile)));
    }

    private void loadData() throws Exception {
        List<LabProblem> listOfLabProblems = new ArrayList<>();
        DocumentBuilderFactory documentBuilderFactory =
                DocumentBuilderFactory.newInstance();

        DocumentBuilder documentBuilder =
                documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(XMLfile);
        Element root = document.getDocumentElement();

        NodeList nodes = root.getChildNodes();
        int len = nodes.getLength();
        for (int i = 0; i < len; i++) {
            Node labProblemNode = nodes.item(i);
            if (labProblemNode instanceof Element) {
                LabProblem b = createLabProblem((Element) labProblemNode);
                listOfLabProblems.add(b);
            }
        }
        Map<Long, LabProblem> map= new HashMap<>();
        for (LabProblem s : listOfLabProblems){
            map.putIfAbsent(s.getId(),s);
        }
        this.setEntities(map);
    }

    private LabProblem createLabProblem(Element labProblemNode) {
        String statement = labProblemNode.getAttribute("problemStatement");
        LabProblem b = new LabProblem();
        b.setProblemStatement(statement);

        b.setTeacher(getTextFromTagName(labProblemNode, "teacher"));
        b.setWeight(Integer.valueOf(getTextFromTagName(labProblemNode, "difficulty")));
        b.setDueDate(getTextFromTagName(labProblemNode, "dueDate"));
        b.setSubject(getTextFromTagName(labProblemNode, "subject"));
        b.setId(Long.valueOf(getTextFromTagName(labProblemNode, "id")));

        return b;
    }

    private String getTextFromTagName(Element element, String tagName) {
        NodeList elements = element.getElementsByTagName(tagName);
        Node node = elements.item(0);
        return node.getTextContent();
    }
}
