package Services;

import java.util.Scanner;

interface ActionsFactory {
    void runActionsForUser(int actionNumber);

    void runActionsForAdmin(int actionNumber);

    void runActionsToChangeBook(int actionNumber);
}

public class Actions implements ActionsFactory {
    private final int BROWS_BOOKS = 1;
    private final int SEND_TO_ADMIN_NEW_BOOK = 3;
    private final int SEARCH_BOOK_BY_AUTHOR = 2;
    private final int CHANGE_BOOK = 3;
    private final int CHANGE_BOOK_AUTHOR = 1;
    private final int CHANGE_BOOK_TITLE = 2;
    private final int CHANGE_BOOK_DESCRIPTION = 3;
    private int actionsForChange;
    private int bookId;
    private String newDescription;
    private String newTitle;
    private String newAuthor;
    private Scanner input = new Scanner(System.in);

    public void runActionsForUser(int actionNumber) {
        switch (actionNumber) {
            case BROWS_BOOKS:
                BookService.viewBooks();
                break;
            case SEARCH_BOOK_BY_AUTHOR:
                System.out.println("enter the author");
                BookService.searchBook(input.nextLine());
                break;
            case SEND_TO_ADMIN_NEW_BOOK:
                System.out.println("enter the info by book");
                UserService.sendToAdminNewBook(input.nextLine());
                break;
            default:
                System.out.println("enter the 1, 2 or 3.");
                break;
        }
    }

    public void runActionsForAdmin(int actionNumber) {
        switch (actionNumber) {
            case BROWS_BOOKS:
                BookService.viewBooks();
                break;
            case SEARCH_BOOK_BY_AUTHOR:
                System.out.println("enter the author");
                BookService.searchBook(input.nextLine());
                break;
            case CHANGE_BOOK:
                System.out.println("1 - change author 2 - change book title  3 - change book description");
                actionsForChange = Integer.parseInt(input.nextLine());
                runActionsToChangeBook(actionsForChange);
                break;
            default:
                System.out.println("enter the 1, 2 or 3.");
                break;
        }
    }

    public void runActionsToChangeBook(int actionNumber) {
        switch (actionNumber) {
            case CHANGE_BOOK_AUTHOR:
                System.out.println("enter the id book ");
                bookId = Integer.parseInt(input.nextLine());
                System.out.println("enter new author");
                newAuthor = input.nextLine();
                BookService.changeBookAuthor(newAuthor, bookId);
                break;
            case CHANGE_BOOK_TITLE:
                System.out.println("enter the id book ");
                bookId = Integer.parseInt(input.nextLine());
                System.out.println("enter new title");
                newTitle = input.nextLine();
                BookService.changeBookTitle(newTitle, bookId);
                break;
            case CHANGE_BOOK_DESCRIPTION:
                System.out.println("enter the id book ");
                bookId = Integer.parseInt(input.nextLine());
                System.out.println("enter new description");
                newDescription = input.nextLine();
                BookService.changeBookDescription(newDescription, bookId);
                break;
            default:
                System.out.println("enter the 1, 2 or 3.");
                break;
        }
    }


}
