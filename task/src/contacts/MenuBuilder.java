package contacts;

import java.util.Scanner;

public class MenuBuilder {
    private String choiseMain = "";
    private String type;
    BookManager bookManager;
    MenuBuilder (BookManager bookManager) {
        this.bookManager = bookManager;
    }
    public void callMainMenu(Scanner scanner){
        System.out.println("Enter action (add, remove, edit, count, info, exit): ");
        choiseMain = scanner.nextLine();
        switch (choiseMain) {
            case ("add"):
                System.out.print("Enter the type (person, organization): ");
                type = scanner.nextLine();
                bookManager.add(scanner, type);
                System.out.println();
                this.callMainMenu(scanner);
                break;
            case ("remove"):
                if (!bookManager.check()) {
                    System.out.println("No records to remove!");
                    System.out.println();
                    this.callMainMenu(scanner);
                    break;
                } else {
                    System.out.println("Select a record: ");
                    if (scanner.hasNextLine())
                        bookManager.remove(Integer.parseInt(scanner.nextLine()));
                    System.out.println();
                    this.callMainMenu(scanner);
                    break;
                }
            case ("edit"):
                if (!bookManager.check()) {
                    System.out.println("No records to edit!");
                    System.out.println();
                    this.callMainMenu(scanner);
                    break;
                } else {
                    int index;
                    System.out.println("Select a record: ");
                    if (scanner.hasNextLine()) {
                        index = Integer.parseInt(scanner.nextLine());
                        bookManager.edit(index, scanner);
                    }
                    System.out.println();
                    this.callMainMenu(scanner);
                    break;
                }
            case ("count"):
                bookManager.count();
                System.out.println();
                this.callMainMenu(scanner);
                break;
            case ("info"):
                bookManager.list();
                System.out.print("Enter index to show info: ");
                if (scanner.hasNextLine())
                    bookManager.info(Integer.parseInt(scanner.nextLine()));
                System.out.println();
                this.callMainMenu(scanner);
                break;
            case ("exit"):
                break;
            default: System.out.println("Enter action (add, remove, edit, count, info, exit): ");
                break;
        }
    }

}

