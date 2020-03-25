package Menu;

import Services.NoteService;

import java.util.Scanner;

interface MenuOptionsFactory {
    void renderMenu(Scanner input);
}

public class MenuOptions implements MenuOptionsFactory {
    private Scanner input = new Scanner(System.in);


    public void renderMenu(Scanner input) {
        System.out.println("Hello");
        System.out.println("Select menu item\n" +
                "1 - View notes\n" +
                "2 - Search for notes\n" +
                "3 - Find a record by a specific word\n" +
                "4 - Add note");
        int answer = Integer.parseInt(input.nextLine());
        runMainMenu(answer);
    }

    private void runMainMenu(int answer) {
        NoteService.loadNotes();
        switch (answer) {
            case MainMenuActions.VIEW_NOTES:
                NoteService.viewNotes();
                break;
            case MainMenuActions.SEARCH_NOTES:
                runMenuSearch();
                break;
            case MainMenuActions.FIND_NOTE_BY_SPECIFIC_WORD:
                System.out.println("enter the word");
                NoteService.findNotesByText(input.nextLine());
                break;
            case MainMenuActions.ADD_NOTE:
                NoteService.addNote();
                break;
        }
    }

    private void runMenuSearch() {
        System.out.println("Select menu item\n" +
                "1 - Email Search\n" +
                "2 - Search by theme\n" +
                "3 - Date Search\n" +
                "4 - Search by date and theme");
        int answer = Integer.parseInt(input.nextLine());
        switch (answer) {
            case MenuSearchActions.FIND_NOTES_BY_EMAIL:
                System.out.println("enter email");
                NoteService.findNotesByEmail(input.nextLine());
                break;
            case MenuSearchActions.FIND_NOTES_BY_THEME:
                System.out.println("enter theme");
                NoteService.findNotesByTheme(input.nextLine());
                break;
            case MenuSearchActions.FIND_NOTES_BY_DATE:
                System.out.println("enter date");
                NoteService.findNotesByDate(input.nextLine());
                break;
            case MenuSearchActions.FIND_NOTES_BY_THEME_AND_DATE:
                System.out.println("enter date");
                String date = input.nextLine();
                System.out.println("enter theme");
                String theme = input.nextLine();
                NoteService.findNotesByDateAndTheme(date, theme);
                break;
        }
    }

}
