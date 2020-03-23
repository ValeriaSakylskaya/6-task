package Services;

import java.util.Scanner;

public class MenuOptions {
    private NoteService noteService = new NoteService();
    private Scanner input = new Scanner(System.in);
    private int Answer;

    public MenuOptions (int answer) {
        noteService.loadNotes();
        switch (answer){
            case 1:
                noteService.viewNotes();
                break;
            case 2:
                searchNotes();
                break;
            case 3:
                searchBySpecificWord();
                break;
            case 4:
                noteService.addNote();
        }
    }
    private void searchNotes(){
        System.out.println("Select menu item\n" +
                "1 - Email Search\n" +
                "2 - Search by theme\n" +
                "3 - Date Search\n" +
                "4 - Search by date and theme");
        Answer = Integer.parseInt(input.nextLine());
        switch (Answer){
            case 1:
                System.out.println("enter the email");
                noteService.findNotesByEmail(input.nextLine());
                break;
            case 2:
                System.out.println("enter the theme");
                noteService.findNotesByTheme(input.nextLine());
                break;
            case 3:
                System.out.println("enter the date");
                noteService.findNotesByDate(input.nextLine());
                break;
            case 4:
                String date, theme;
                System.out.println("enter the date");
                date = input.nextLine();
                System.out.println("enter the theme");
                theme = input.nextLine();
                noteService.findNotesByDateAndTheme(date,theme);
                break;
        }
    }
    private void searchBySpecificWord(){
       System.out.println("enter the word");
       noteService.findNotesByText(input.nextLine());
    }
}
