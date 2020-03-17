package com.task;

import Enums.Role;
import Services.BookService;
import Services.UserService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String Login;
        String Pass;
        String newTitle;
        String newAuthor;
        int idBook;
        String newDescription;
        Scanner input = new Scanner(System.in);


        System.out.println("login");
        Login = input.nextLine();
        System.out.println("pass");
        Pass = input.nextLine();
        UserService userService = new UserService();
        userService.authenticationUser(Login, Pass);
        BookService bookService = new BookService();
        if (userService.getRole(Login, Pass) == Role.User) {
            System.out.println("Hello, you can choose the following actions: ");
            System.out.println("1 - browse books 2 - search for books, 3 - suggest add books ");
            switch (Integer.parseInt(input.nextLine())) {
                case (1):
                    bookService.viewBooks();
                    break;
                case (2):
                    System.out.println("enter the author");
                    bookService.searchBook(input.nextLine());
                    break;
                case (3):
                    System.out.println("enter the info by book");
                    userService.sendToAdmin(input.nextLine());
                    break;
                default:
                    System.out.println("enter the 1, 2 or 3.");
                    break;
            }
        }
        if (userService.getRole(Login, Pass) == Role.Admin) {
            System.out.println("Hello, you can choose the following actions: ");
            System.out.println("1 - browse books 2 - search for books,  3 - catalog modification");
            switch (Integer.parseInt(input.nextLine())) {
                case (1):
                    bookService.viewBooks();
                    break;
                case (2):
                    System.out.println("enter the author");
                    bookService.searchBook(input.nextLine());
                    break;
                case (3):
                    System.out.println("1 - change author 2 - change book title  3 - change book description");
                    switch (Integer.parseInt(input.nextLine())) {
                        case (1):
                            System.out.println("enter the id book ");
                            idBook = Integer.parseInt(input.nextLine());
                            System.out.println("enter new author");
                            newAuthor = input.nextLine();
                            bookService.changeBookAuthor(newAuthor, idBook);
                            break;
                        case (2):
                            System.out.println("enter the id book ");
                            idBook = Integer.parseInt(input.nextLine());
                            System.out.println("enter new title");
                            newTitle = input.nextLine();
                            bookService.changeBookTitle(newTitle, idBook);
                            break;
                        case (3):
                            System.out.println("enter the id book ");
                            idBook = Integer.parseInt(input.nextLine());
                            System.out.println("enter new description");
                            newDescription = input.nextLine();
                            bookService.changeBookDescription(newDescription, idBook);
                            break;
                        default:
                            System.out.println("enter the 1, 2 or 3.");
                            break;
                    }
            }

            input.close();
        }
    }
}
