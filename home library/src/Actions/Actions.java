package Actions;

import Services.BookService;
import Services.UserService;

import java.util.Scanner;

interface ActionsFactory {
    void runActionsForUser(int actionNumber);

    void runActionsForAdmin(int actionNumber);


}

public class Actions implements ActionsFactory {
    private int actionsForChange;
    private int bookId;
    private String newDescription;
    private String newTitle;
    private String newAuthor;
    private Scanner input = new Scanner(System.in);

    public void runActionsForUser(int actionNumber) {
        switch (actionNumber) {
            case UserActions.BROWS_BOOKS:
                BookService.viewBooks();
                break;
            case UserActions.SEARCH_BOOK_BY_AUTHOR:
                System.out.println("enter the author");
                BookService.searchBook(input.nextLine());
                break;
            case UserActions.SEND_TO_ADMIN_NEW_BOOK:
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
            case AdminActions.BROWS_BOOKS:
                BookService.viewBooks();
                break;
            case AdminActions.SEARCH_BOOK_BY_AUTHOR:
                System.out.println("enter the author");
                BookService.searchBook(input.nextLine());
                break;
            case AdminActions.CHANGE_BOOK:
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
            case ChangeBookActions.CHANGE_BOOK_AUTHOR:
                System.out.println("enter the id book ");
                bookId = Integer.parseInt(input.nextLine());
                System.out.println("enter new author");
                newAuthor = input.nextLine();
                BookService.changeBookAuthor(newAuthor, bookId);
                break;
            case ChangeBookActions.CHANGE_BOOK_TITLE:
                System.out.println("enter the id book ");
                bookId = Integer.parseInt(input.nextLine());
                System.out.println("enter new title");
                newTitle = input.nextLine();
                BookService.changeBookTitle(newTitle, bookId);
                break;
            case ChangeBookActions.CHANGE_BOOK_DESCRIPTION:
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
