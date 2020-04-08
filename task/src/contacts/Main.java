package contacts;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        BookManager bookManager = new BookManager();
        MenuBuilder menuBuilder = new MenuBuilder(bookManager);
        menuBuilder.callMainMenu(scanner);
    }
}
