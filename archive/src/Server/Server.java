package Server;

import Service.StudentService;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    private static Socket clientSocket; //сокет для общения
    private static ServerSocket server; // серверсокет
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет
    private StudentService studentService = new StudentService();
    private String answer;
    private int studentId;
    private String studentName;
    private String studentSpecialization;
    private final int VIEW_STUDENT_INFO_BY_ID = 1;
    private final int CHANGE_STUDENT_SPECIALIZATION = 2;
    private final int ADD_NEW_STUDENT = 3;

    public Server() throws IOException, ParserConfigurationException, SAXException {
        server = new ServerSocket(4004);
    }

    public void run() throws IOException {
        try {
            System.out.println("Server is running...");
            studentService.loadStudents();
            clientSocket = server.accept();
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                out.write("Hi, this is Server! Choose what you want to do 1 - view the case 2 - make changes 3 - add a new case: " + "\n");
                out.flush();
                boolean isConnectionClosed = false;
                while (!isConnectionClosed) {
                    int messageType = in.read();
                    switch (messageType) {
                        case VIEW_STUDENT_INFO_BY_ID:
                            viewStudentInfoById();
                            break;
                        case CHANGE_STUDENT_SPECIALIZATION:
                            changeStudentSpecialization();
                            break;
                        case ADD_NEW_STUDENT:
                            addNewStudent();
                            break;
                        default:
                            isConnectionClosed = true;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Server closed!");
            server.close();
        }
    }

    private void viewStudentInfoById() throws IOException {
        studentId = in.read();
        answer = studentService.viewStudentById(studentId);

        out.write(answer + "\n");
        out.flush();
    }

    private void changeStudentSpecialization() throws IOException, TransformerException, SAXException, ParserConfigurationException {
        studentId = in.read();
        studentSpecialization = in.readLine();
        studentService.changeSpecialization(String.valueOf(studentId), studentSpecialization);
        out.write("data changed" + "\n");
        out.flush();
    }

    private void addNewStudent() throws IOException, TransformerException, SAXException, ParserConfigurationException, InterruptedException {
        studentId = in.read();
        studentName = in.readLine();
        studentSpecialization = in.readLine();
        studentService.addNewStudent(String.valueOf(studentId), studentName, studentSpecialization);
        out.write("student added" + "\n");
        out.flush();
    }
}