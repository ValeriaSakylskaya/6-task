package com.task;

import Services.MenuOptions;
import Services.NoteService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
      int Answer;
      System.out.println("Hello");
      System.out.println("Select menu item\n" +
              "1 - View notes\n" +
              "2 - Search for notes\n" +
              "3 - Find a record by a specific word\n" +
              "4 - Add note");
      Answer = Integer.parseInt(input.nextLine());
        MenuOptions menuOptions = new MenuOptions(Answer);

    }
}
