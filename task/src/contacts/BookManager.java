package contacts;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookManager {
    private List<Record> phoneBook = new ArrayList<Record>(0);

    public void add (Scanner scanner, String type) {
        phoneBook.add(RecordCreator.createRecord(scanner, type));
        System.out.println("The record added!");
    }
    public Boolean remove (int i) {
        Boolean removed = false;
        i--; //because in list first index is 0
        if (phoneBook.size() > i) {
            phoneBook.remove(i);
            removed = true;
            System.out.println("The record removed!");
        } else System.out.println("No records to remove!");
        return  removed;
    }
    public Boolean edit (int index, Scanner scanner) {
        index--; //because in list first index is 0
        Boolean edited = false;
        if (index < phoneBook.size()) {
            if (phoneBook.get(index) instanceof Person) {
                System.out.println("Select a field (name, surname, birth, gender, number): ");
            } else if (phoneBook.get(index) instanceof Organization) {
                System.out.println("Select a field (organization name, address, number): ");
            }
                phoneBook.get(index).changeParameter(scanner.nextLine());
            System.out.println("The record updated!");
        } else System.out.println("No records to edit!");
        return edited;
    }
    public void list() {
        if (!phoneBook.isEmpty()) {
            for (int i = 0; i < phoneBook.size(); i++) {
                System.out.println((i + 1) + ". " + phoneBook.get(i).getShortName());
            }

        } else {System.out.println("[no number]");}
    }
    public void count() {
        System.out.println("The Phone Book has " + phoneBook.size() + " records.");
    }
    public Boolean check() {
        return (phoneBook.size() > 0) ?  true : false;
    }
    public void info(int index) {
        System.out.println(phoneBook.get(index - 1));
    }
}


