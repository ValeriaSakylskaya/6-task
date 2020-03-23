package com.task;

import Enums.Role;
import Services.Actions;
import Services.BookService;
import Services.UserService;

import java.util.Scanner;

public class Main {
    /*Задание 1: создать консольное приложение “Учет книг в домашней библиотеке”.
    Общие требования к заданию:
    • Система учитывает книги как в электронном, так и в бумажном варианте.
    • Существующие роли: пользователь, администратор.
    • Пользователь может просматривать книги в каталоге книг, осуществлять поиск
    книг в каталоге.
    • Администратор может модифицировать каталог.
    • *При добавлении описания книги в каталог оповещение о ней рассылается на
    e-mail всем пользователям
    • **При просмотре каталога желательно реализовать постраничный просмотр
    • ***Пользователь может предложить добавить книгу в библиотеку, переслав её
    администратору на e-mail.
    • Каталог книг хранится в текстовом файле.
    • Данные аутентификации пользователей хранятся в текстовом файле. Пароль
    не хранится в открытом виде*/
    public static void main(String[] args) {
        String Login;
        String Pass;
        String newTitle;
        String newAuthor;
        int idBook;
        int action;
        String newDescription;
        Scanner input = new Scanner(System.in);
        Actions actions = new Actions();
        UserService userService = new UserService();
        userService.loadUsers();
        userService.login();
        BookService bookService = new BookService();
        bookService.loadBooks();
        if (userService.getRole(Login, Pass) == Role.User) {
            System.out.println("Hello, you can choose the following actions: ");
            System.out.println("1 - browse books 2 - search for books, 3 - suggest add books ");
            action = Integer.parseInt(input.nextLine());
            actions.runActionsForUser(action);
            input.close();
        }
        if (userService.getRole(Login, Pass) == Role.Admin) {
            System.out.println("Hello, you can choose the following actions: ");
            System.out.println("1 - browse books 2 - search for books,  3 - catalog modification");
            action = Integer.parseInt(input.nextLine());
            actions.runActionsForAdmin(action);
            input.close();
        }
    }
}
