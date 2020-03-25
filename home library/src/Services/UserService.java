package Services;

import Actions.Actions;
import Enums.Role;
import Models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserService {
    private static List<User> users = new ArrayList<User>();
    private static Sender sender = new Sender("checkkurs@gmail.com", "1236894qwerty");
    private static User currentUser;

    public UserService() {

    }

    private void loadUsers() {
        final String cave = "pass.txt";
        String line;
        String[] subLine;
        try {
            File file = new java.io.File(cave);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            while ((line = reader.readLine()) != null) {
                subLine = line.split(";", 5);
                if (subLine[3].equalsIgnoreCase(Role.Admin.toString()))
                    users.add(new User(subLine[0], subLine[1], subLine[2], subLine[4], Role.Admin));
                if (subLine[3].equalsIgnoreCase(Role.User.toString()))
                    users.add(new User(subLine[0], subLine[1], subLine[2], subLine[4], Role.User));
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
        currentUser = authenticateUser(login, password);
        if (currentUser != null) {
            System.out.println("User is logged");
        }
    }

    private User authenticateUser(String login, String password) {
        PasswordUtils passwordUtils = new PasswordUtils();

        loadUsers();
        for (User user : users) {
            if (user.getLogin().equals(login.trim())) {
                if (passwordUtils.verifyUserPassword(password, user.getPassword(), user.getSalt())) {
                    return user;
                } else {
                    System.out.println("Password is not correct");
                }
            } else {
                System.out.println("User not found");
            }
        }
        return null;
    }

    public Role getRole(User user) {
        return user.getRole();
    }

    public void renderMenu(Scanner input) {
        Actions actions = new Actions();

        System.out.println("You can choose the following actions: ");
        if (getRole(currentUser) == Role.User) {
            System.out.println("1 - browse books 2 - search for books, 3 - suggest add books ");
            int action = Integer.parseInt(input.nextLine());
            actions.runActionsForUser(action);
        }
        if (getRole(currentUser) == Role.Admin) {
            System.out.println("1 - browse books 2 - search for books,  3 - catalog modification");
            int action = Integer.parseInt(input.nextLine());
            actions.runActionsForAdmin(action);
        }
    }
}
