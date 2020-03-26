package Service;

import Modules.Student;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

public class StudentService {
    private List<Student> studentList = new ArrayList<Student>();
    private final String studentsSourcePath = "I:\\prj\\repos\\6-task\\archive\\file.xml";

    public StudentService() throws ParserConfigurationException, SAXException, IOException {

    }

    public void loadStudents() throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(studentsSourcePath));
        NodeList studentElements = document.getDocumentElement().getElementsByTagName("student");
        for (int i = 0; i < studentElements.getLength(); i++) {
            Node student = studentElements.item(i);
            NamedNodeMap attributes = student.getAttributes();
            studentList.add(new Student(Integer.parseInt(attributes.getNamedItem("id").getNodeValue()),
                    attributes.getNamedItem("nameStudent").getNodeValue(),
                    attributes.getNamedItem("Specialization").getNodeValue()));
        }
    }

    public void viewStudent() {
        for (Student student : studentList)
            System.out.println(student);
    }

    public String viewStudentById(int id) {
        StringBuilder builder = new StringBuilder();
        for (Student student : studentList) {
            if (student.getId() == id)
                builder.append(student.toString());
        }

        return builder.toString();
    }

    public void changeSpecialization(String studentId, String specialization) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(studentsSourcePath));
        NodeList studentElements = document.getDocumentElement().getElementsByTagName("student");
        for (int i = 0; i < studentElements.getLength(); i++) {
            Node student = studentElements.item(i);
            NamedNodeMap attributes = student.getAttributes();
            if (attributes.getNamedItem("id").getNodeValue().equals(studentId)) {
                Node specializationNode = attributes.getNamedItem("Specialization");
                specializationNode.setTextContent(specialization);
            }
        }
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(studentsSourcePath));
        transformer.transform(source, result);
        System.out.println("CHANGE SAVE");
    }

    public void addNewStudent(String id, String name, String specialization) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(studentsSourcePath));
        Node studentsNode = document.getElementsByTagName("students").item(0);
        Element studentElement = document.createElement("student");
        studentElement.setAttribute("id", id);
        studentElement.setAttribute("Specialization", specialization);
        studentElement.setAttribute("nameStudent", name);
        studentsNode.appendChild(studentElement);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(studentsSourcePath));
        transformer.transform(source, result);
        System.out.println("CHANGE SAVE");
    }
}
