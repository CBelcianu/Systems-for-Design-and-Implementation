package ro.ubb.labproblems.Repository;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ro.ubb.labproblems.Domain.Grade;
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

public class GradeXMLRepository extends InMemoryRepository<Long, Grade> {
    private String XMLfile;

    public GradeXMLRepository(Validator<Grade> GradeValidator, String XMLfile){
        super(GradeValidator);
        this.XMLfile=XMLfile;
        try {
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Optional<Grade> addToRepo(Grade entity) throws ValidatorException {
        Optional<Grade> optional = super.addToRepo(entity);
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

    public void saveToFile(Grade entity) throws Exception{
        Document document = DocumentBuilderFactory
                .newInstance()
                .newDocumentBuilder()
                .parse(XMLfile);
        Element root = document.getDocumentElement();
        Element gradeElement = document.createElement("grade");
        gradeElement.setAttribute("studentid",String.valueOf(entity.getStudentID()));
        root.appendChild(gradeElement);

        appendChildWithText(document, gradeElement, "problemid", String.valueOf(entity.getProblemID()));
        appendChildWithText(document, gradeElement, "grade", String.valueOf(entity.getGrade()));
        appendChildWithText(document, gradeElement, "id", entity.getId().toString());

        Transformer transformer =
                TransformerFactory.newInstance().newTransformer();
        transformer.transform(new DOMSource(root),
                new StreamResult(new FileOutputStream(
                        XMLfile)));
    }

    private void loadData() throws Exception {
        List<Grade> listOfGrade = new ArrayList<>();
        DocumentBuilderFactory documentBuilderFactory =
                DocumentBuilderFactory.newInstance();

        DocumentBuilder documentBuilder =
                documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(XMLfile);
        Element root = document.getDocumentElement();

        NodeList nodes = root.getChildNodes();
        int len = nodes.getLength();
        for (int i = 0; i < len; i++) {
            Node gradeNode = nodes.item(i);
            if (gradeNode instanceof Element) {
                Grade b = createGrade((Element) gradeNode);
                listOfGrade.add(b);
            }
        }
        Map<Long, Grade> map= new HashMap<>();
        for (Grade s : listOfGrade){
            map.putIfAbsent(s.getId(),s);
        }
        this.setEntities(map);
    }

    private Grade createGrade(Element gradeNode) {
        int sid = Integer.valueOf(gradeNode.getAttribute("studentid"));
        Grade b = new Grade();
        b.setStudentID(sid);

        b.setProblemID(Integer.valueOf(getTextFromTagName(gradeNode, "problemid")));
        b.setGrade(Integer.valueOf(getTextFromTagName(gradeNode, "grade")));
        b.setId(Long.valueOf(getTextFromTagName(gradeNode, "id")));

        return b;
    }

    private String getTextFromTagName(Element element, String tagName) {
        NodeList elements = element.getElementsByTagName(tagName);
        Node node = elements.item(0);
        return node.getTextContent();
    }
}
