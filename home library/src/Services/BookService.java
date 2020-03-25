package Services;

import Models.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import Enums.TypeOfBookCarrier;

import java.util.Scanner;

public class BookService {
    private static List<Book> books = new ArrayList<Book>();
    private static int defaultPageIndex = 0;
    private static UserService userService = new UserService();
    private static final int EXIT_COMMAND = 3;
    private static final int SCROLL_FORWARD = 1;
    private static final int SCROLL_BACK = 2;

    public BookService() {
    }

    public static void viewBooks() {
        int Counter = 0;
        Scanner input = new Scanner(System.in);

        while (Counter != EXIT_COMMAND) {
            switch (Counter) {
                case SCROLL_FORWARD:
                    defaultPageIndex = defaultPageIndex + 3;
                    break;
                case SCROLL_BACK:
                    defaultPageIndex = defaultPageIndex - 3;
                    break;
                default:
                    defaultPageIndex = 0;
            }
            viewBooksByIndex(defaultPageIndex);
            if (defaultPageIndex == 0)
                System.out.println("next - 1; exit - 3");
            else
                System.out.println("next - 1 ; back - 2; exit - 3");
            Counter = Integer.parseInt(input.nextLine());
        }
    }

    private static void viewBooksByIndex(int index) {
        for (int i = index; i < (index + 3); i++) {
            System.out.println(books.get(i));
        }
    }

    public void loadBooks() {
        final String cave = "allBooks.txt";
        String line;
        String[] subLine;
        TypeOfBookCarrier typeOfBookCarrier;
        try {
            File file = new java.io.File(cave);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            while ((line = reader.readLine()) != null) {
                subLine = line.split(";", 5);
                if (subLine[3].equalsIgnoreCase(TypeOfBookCarrier.Electronic.toString()))
                    books.add(new Book(Integer.parseInt(subLine[0]), subLine[2], subLine[1], TypeOfBookCarrier.Electronic, subLine[4]));
                if (subLine[3].equalsIgnoreCase(TypeOfBookCarrier.Paper.toString()))
                    books.add(new Book(Integer.parseInt(subLine[0]), subLine[2], subLine[1], TypeOfBookCarrier.Paper, subLine[4]));
            }
            reader.close();
            fr.close();
            System.out.println("Welcome to library");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void searchBook(String author) {
        List<Book> foundBook = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author))
                foundBook.add(book);
        }
        if (foundBook.isEmpty())
            System.out.println("Books by this author don't found");
        else
            for (Book book : foundBook) {
                book.toString();
            }
    }

    public static void changeBookDescription(String description, int id) {
        books.get(id - 1).setDescription(description);
        String textEmail = "In book " + books.get(id - 1).getName() + " author :" + books.get(id - 1).getAuthor() +
                "change description : " + description;
        saveBooks();
        userService.sendToAllUsers(textEmail);

    }

    public static void changeBookTitle(String title, int id) {
        books.get(id - 1).setName(title);
        saveBooks();

    }

    public static void changeBookAuthor(String author, int id) {
        books.get(id - 1).setAuthor(author);
        saveBooks();

    }

    public static void saveBooks() {
        String saveModel = mapToServerModel();
        try (FileWriter writer = new FileWriter("allBooks.txt", false)) {
            writer.write(saveModel);
            writer.flush();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }

    }

    private static String mapToServerModel() {
        StringBuilder builder = new StringBuilder();
        String string;
        for (Book book : books) {
            string = book.getID() + ";" + book.getAuthor() + ";" + book.getName() + ";" + book.getTypeOfBookCarrier() + ";" + book.getDescription() + "\n";
            builder.append(string);
        }
        return builder.toString();
    }


}
