package Modules;

import java.io.*;
import java.net.Socket;


public class Client {
    private static Socket clientSocket;
    private static BufferedReader reader;
    private static BufferedReader in;
    private static BufferedWriter out;
    private String answer;
    private int studentId;
    private String studentName;
    private final int VIEW_STUDENT_INFO_BY_ID = 1;
    private final int CHANGE_STUDENT_SPECIALIZATION = 2;
    private final int ADD_NEW_STUDENT = 3;
    private String studentSpecialization;

    public Client() throws IOException {
        clientSocket = new Socket("localhost", 4004);
        reader = new BufferedReader(new InputStreamReader(System.in));
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
    }

    public void run() {
        try {
            try {
                System.out.println("connected:");
                String serverWord = in.readLine(); // ждём, что скажет сервер
                System.out.println(serverWord); // получив - выводим на экран
                int task = Integer.parseInt(reader.readLine());
                out.write(task);
                out.flush();
                switch (task) {
                    case VIEW_STUDENT_INFO_BY_ID:
                        viewStudentInfoById();
                        break;
                    case CHANGE_STUDENT_SPECIALIZATION:
                        changeStudentSpecialization();
                        break;
                    case ADD_NEW_STUDENT:
                        addNewStudent();
                    default:
                        break;
                }

            } finally {
                System.out.println("Client was closed...");
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private void viewStudentInfoById() throws IOException {
        System.out.println("enter student id");
        studentId = Integer.parseInt(reader.readLine());
        out.write(studentId);
        out.flush();
        answer = in.readLine();
        System.out.println(answer);
    }

    private void changeStudentSpecialization() throws IOException {
        System.out.println("enter student id");
        studentId = Integer.parseInt(reader.readLine());
        out.write(studentId + "\n");
        out.flush();
        System.out.println("enter new specialization");
        String specialization = reader.readLine();
        out.write(specialization + "\n");
        out.flush();
        answer = in.readLine();
        System.out.println(answer);
    }

    private void addNewStudent() throws IOException {
        System.out.println("enter student id");
        studentId = Integer.parseInt(reader.readLine());
        out.write(studentId + "\n");
        out.flush();
        System.out.println("enter student name");
        studentName = reader.readLine();
        out.write(studentName + "\n");
        out.flush();
        System.out.println("enter specialization name");
        studentSpecialization = reader.readLine();
        out.write(studentName + "\n");
        out.flush();
        answer = in.readLine();
        System.out.println(answer);
    }

}