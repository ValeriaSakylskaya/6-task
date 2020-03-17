package Service;

import Modules.FileStudent;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;
public class FileStudentService {
    private List<FileStudent> students = new ArrayList<FileStudent>();
    public FileStudentService() throws ParserConfigurationException, SAXException, IOException {
        loadFromXml();
    }
    private void loadFromXml() throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("I:\\prj\\repos\\final task\\archive\\file.xml"));
        NodeList studentElements = document.getDocumentElement().getElementsByTagName("student");
        for (int i = 0; i < studentElements.getLength(); i++) {
            Node student = studentElements.item(i);
            NamedNodeMap attributes = student.getAttributes();
            students.add(new FileStudent(Integer.parseInt(attributes.getNamedItem("id").getNodeValue()),
                    attributes.getNamedItem("nameStudent").getNodeValue(),
                    attributes.getNamedItem("Specialization").getNodeValue()));
        }
    }
    public void viewStudent(){
        for(FileStudent fileStudent : students){
            System.out.println(fileStudent);
        }
    }
    public  String viewStudentById(int id){
       StringBuilder builder = new StringBuilder();
        for(FileStudent fileStudent : students){
            if (fileStudent.getId() == id){
                builder.append(fileStudent.toString());
            }
        }

        return builder.toString();
    }
    public void changeSpecialization(String id, String specialization ) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("I:\\prj\\repos\\final task\\archive\\file.xml"));
        NodeList studentElements = document.getDocumentElement().getElementsByTagName("student");
        for (int i = 0; i < studentElements.getLength(); i++) {
            Node student = studentElements.item(i);
            NamedNodeMap attributes = student.getAttributes();
            if(attributes.getNamedItem("id").getNodeValue().equals(id)){
                Node specializationNode = attributes.getNamedItem("Specialization");
                specializationNode.setTextContent(specialization);
            }
        }
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File("I:\\prj\\repos\\final task\\archive\\file.xml"));
        transformer.transform(source, result);
        System.out.println("chANGE SAVE");
    }
    public void addNewStudent (String id, String name, String specialization) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("I:\\prj\\repos\\final task\\archive\\file.xml"));
        Node students = document.getElementsByTagName("students").item(0);
        Element student = document.createElement("student");
        student.setAttribute("id",id);
        student.setAttribute("Specialization",specialization);
        student.setAttribute("nameStudent",name);
        students.appendChild(student);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File("I:\\prj\\repos\\final task\\archive\\file.xml"));
        transformer.transform(source, result);
        System.out.println("chANGE SAVE");
    }
}
