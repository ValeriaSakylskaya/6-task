package Services;

import Enums.Role;
import Models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserService {
    private static List<User> users = new ArrayList<User>();
    private static Sender sender = new Sender("checkkurs@gmail.com", "1236894qwerty");

    public UserService() {

    }

    public void loadUsers() {
        final String cave = "pass.txt";
        String line;
        String[] subLine;
        try {
            File file = new java.io.File(cave);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            int i = 0;
            while ((line = reader.readLine()) != null) {
                subLine = line.split(";", 5);
                if (subLine[3].equalsIgnoreCase(Role.Admin.toString()))
                    users.add(new User(subLine[0], subLine[1], subLine[2], subLine[4], Role.Admin));
                if (subLine[3].equalsIgnoreCase(Role.User.toString()))
                    users.add(new User(subLine[0], subLine[1], subLine[2], subLine[4], Role.User));
                i++;
            }
            reader.close();
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendToAllUsers(String text) {
        for (User user : users) {
            sender.send("book changes", text, user.getEmail());
        }
    }

    public static void sendToAdminNewBook(String text) {
        for (User user : users) {
            if (user.getRole() == Role.Admin)
                sender.send("book changes", text, user.getEmail());
        }
    }

    public void login() {
        Scanner input = new Scanner(System.in);
        String login, password;
        System.out.println("login");
        login = input.nextLine();
        System.out.println("pass");
        password = input.nextLine();
        authenticateUser(login, password);
    }

    private void authenticateUser(String login, String password) {
        int Counter = 0;
        PasswordUtils passwordUtils = new PasswordUtils();
        for (User user : users) {
            if (user.getLogin().equals(login.trim()))
                Counter = 1;
        }
        if (Counter > 0)
            System.out.println("User found");
        else
            System.out.println("User doesn't found");
        Counter = 0;
        for (User user : users) {
            if (passwordUtils.verifyUserPassword(password, user.getPassword(), user.getSalt()))
                Counter = 1;
        }
        if (Counter > 0)
            System.out.println("password is correct");
        else
            System.out.println("password is not correct");

    }

    public Role getRole(String login, String pass) {
        Role role = Role.User;
        PasswordUtils passwordUtils = new PasswordUtils();
        for (User user : users) {
            if (user.getLogin().equals(login.trim()) && passwordUtils.verifyUserPassword(pass, user.getPassword(), user.getSalt()))
                role = user.getRole();
        }
        return role;
    }
    public
}
