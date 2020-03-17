package Modules;

import java.io.*;
import java.net.Socket;


public class Client {
    private static Socket clientSocket; //сокет для общения
    private static BufferedReader reader; // нам нужен ридер читающий с консоли, иначе как
    // мы узнаем что хочет сказать клиент?
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет
    private String answer;
    private int Id;
    private String Name;
    private String Specialization;
    public Client() throws IOException {
        try {
            try {
                // адрес - локальный хост, порт - 4004, такой же как у сервера
                clientSocket = new Socket("localhost", 4004); // этой строкой мы запрашиваем
                //  у сервера доступ на соединение
                reader = new BufferedReader(new InputStreamReader(System.in));
                // читать соообщения с сервера
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                // писать туда же
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                System.out.println("connected:");
                String serverWord = in.readLine(); // ждём, что скажет сервер
                System.out.println(serverWord); // получив - выводим на экран
                int task = Integer.parseInt(reader.readLine());
                out.write(task);
                out.flush();
                switch (task){
                    case(1):
                        System.out.println("enter the id student");
                         Id = Integer.parseInt(reader.readLine());
                        out.write(Id);
                        out.flush();
                        answer = in.readLine();
                        System.out.println(answer);
                        break;
                    case(2):
                        System.out.println("enter the id student");
                        Id = Integer.parseInt(reader.readLine());
                        out.write(Id);
                        out.flush();
                        answer = in.readLine();
                        System.out.println("you choose " + answer);
                        System.out.println(" enter new specialization");
                        String specialization = reader.readLine();
                        out.write(specialization+"\n");
                        out.flush();
                        answer = in.readLine();
                        System.out.println(answer);
                        break;
                    case(3):
                        System.out.println("enter the id student");
                       String Idd = reader.readLine();
                        out.write(Idd+"\n");
                        out.flush();
                       System.out.println("enter the name student");
                        Name = reader.readLine();
                        out.write(Name);
                        out.flush();
                      /*   System.out.println("enter the specialization student");
                        Specialization = reader.readLine();
                        out.write(Specialization);
                        out.flush();*/
                        answer = in.readLine();
                        System.out.println(answer);
                    default:
                        break;
                }
             //   System.out.println(word);
             //   out.write(word);
              //  out.flush();

            } finally { // в любом случае необходимо закрыть сокет и потоки
                System.out.println("Клиент был закрыт...");
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}