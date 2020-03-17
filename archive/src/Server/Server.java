package Server;

import Service.FileStudentService;
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
    private FileStudentService fileStudentService = new FileStudentService();
    private String answer;
    private int Id;
    private String Name;
    private String Specialization;

    public Server() throws IOException, ParserConfigurationException, SAXException {
        try {
            try {
                server = new ServerSocket(4004); // серверсокет прослушивает порт 4004
                System.out.println("Сервер запущен!");
                clientSocket = server.accept();
                try {
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                    out.write("Hi, this is Server! Choose what you want to do 1 - view the case 2 - make changes 3 - add a new case: "  + "\n");
                    out.flush();
                    boolean done = false;
                    while(!done) {
                        int messageType = in.read();
                        switch(messageType) {
                            case 1:
                                Id = in.read();
                                answer = fileStudentService.viewStudentById(Id);
                                out.write(answer +  "\n");
                                out.flush();
                                break;
                            case 2:
                                 Id = in.read();
                                answer = fileStudentService.viewStudentById(Id);
                                out.write(answer +  "\n");
                                out.flush();
                               Specialization = in.readLine();
                                fileStudentService.changeSpecialization(String.valueOf(Id), Specialization);
                               out.write("data changed"+  "\n");
                               out.flush();
                                break;
                            case 3:
                               String Idd = in.readLine();
                                System.out.println(Idd);
                                 Name = in.readLine();
                                System.out.println(Name);
                             //   Specialization = in.readLine();
                             //   System.out.println(Specialization);
                            //    fileStudentService.addNewStudent(String.valueOf(Id),Name, Specialization);
                               out.write("data changed"+  "\n");
                             out.flush();
                                break;
                            default:
                                done = true;

                        }
                    }
                   // String    word=in.readLine();
                   // System.out.println(word);
                } finally {
                    System.out.println("dfjkhgkdf");
                    clientSocket.close();
                    in.close();
                    out.close();
                }
            } finally {
                System.out.println("Сервер закрыт!");
                server.close();
            }
        } catch (IOException | TransformerException e) {
            System.err.println(e);
        }
    }
}