package contacts;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecordCreator {
    public static Record createRecord(Scanner scanner, String type){
        switch (type.toLowerCase()) {
            case "person":
                return new Person(scanner);
            case "organization":
                return new Organization(scanner);
            default:
                System.out.println("Bad type!");
                return null;
        }
    }
}
abstract class Record {
    String number;
    String input = "";
    LocalDateTime timeCreated, lastTimeEdited;
    protected Record () {
        this.number = "[no number]";
        this.timeCreated = LocalDateTime.now();
        this.lastTimeEdited = timeCreated;

    }

    public String getNumber() {
        return number;
    }

    public void setLastTimeEdited() {
        this.lastTimeEdited = LocalDateTime.now();
    }

    public void setNumber(Scanner scanner) {
        input = scanner.nextLine();
        if (checkNumber(input)) this.number = input;
    }
    private boolean checkNumber(String number) {
        if (Pattern.compile("\\+?\\([\\w]+\\)((\\-| )\\w{2,})*").matcher(number).matches() ||
                Pattern.compile("\\+?[\\w]+((\\-| )\\(\\w{2,}\\))((\\-| )\\w{2,})*").matcher(number).matches() ||
                Pattern.compile("\\+?[\\w]+((\\-| )\\w{2,})*").matcher(number).matches()) {
            return true;
        } else {
            return false;
        }
    }

    abstract String getShortName();
    abstract void changeParameter(String parameter);

    @Override
    public String toString() {
        return  "Number: " + number + '\n' +
                // "Time created: " + " "  + '\n' +
               // "Time last edit: " + " ";
        "Time created: " + timeCreated.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"))   + '\n' +
         "Time last edit: " +  lastTimeEdited.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
    }
}
class Person extends Record {
    private String name, surname;
    private String birthDate = "[no data]";
    private String gender = "[no data]";
    Scanner scanner;

    public Person(Scanner scanner){
        this.scanner = scanner;
        setName();
        setSurname();
        setBirthDate();
        setGender();
        super.setNumber(scanner);

    }

    public String getName() {
        return name;
    }

    public void setName() {
        System.out.println("Enter the name: ");
        if (scanner.hasNextLine()) this.name = scanner.nextLine();
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname() {
        System.out.println("Enter the surname: ");
        if (scanner.hasNextLine()) this.surname = scanner.nextLine();
    }
    public Boolean hasNumber() {
       if (this.number != null) return true;
       else return false;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate() {
        String bD = "";
        System.out.println("Enter the birth date: ");
        if (scanner.hasNextLine()) bD = scanner.nextLine();
        if (checkBirthDate(bD)) {
            this.birthDate = bD;
        }
    }
    public Boolean checkBirthDate (String birthDate) throws DateTimeParseException {
        try {
            LocalDate date = LocalDate.parse(birthDate);
            return true;
        } catch (Exception e) {
            System.out.println("Bad birth date!");
            return false;
        }
    }

    public String getGender() {
        return gender;
    }

    public void setGender() {
        System.out.println("Enter the gender (M, F): ");
        String g = scanner.nextLine();
        if (checkGender(g)) {
            this.gender = g;
        }
    }
    public  Boolean checkGender (String gender) {
        gender = gender.toUpperCase();
        if (gender.equals("M")|| gender.equals("F")) return true;
        else {
            System.out.println("Bad gender!");
            return false;
        }
    }

    @Override
    String getShortName() {
        return getName() + " " + getSurname();
    }

    @Override
    void changeParameter(String parameter) {
        switch (parameter.toLowerCase()){
            case "name":
                setName();
                break;
            case "surname":
                setSurname();
                break;
            case "birth":
                setBirthDate();
                break;
            case "gender":
                setGender();
                break;
            case "number":
                super.setNumber(this.scanner);
                break;
            default:
                break;
        }
        super.setLastTimeEdited();
        System.out.println("The record updated!");
    }

    @Override
    public String toString() {
        return   "Name: " + name + '\n' +
                "Surname: " + surname + '\n' +
                "Birth date: " + birthDate + '\n' +
                "Gender: " + gender + '\n' +
                super.toString();
    }
}

class Organization extends Record {
    String companyName = "";
    String address = "";
    Scanner scanner;

    public Organization (Scanner scanner) {
        this.scanner = scanner;
        setCompanyName();
        setAddress();
        super.setNumber(scanner);
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setAddress() {
        System.out.print("Enter the address: ");
        if (scanner.hasNextLine()) this.address = scanner.nextLine();
    }

    public String getAddress() {
        return address;
    }

    public void setCompanyName() {
        if (scanner.hasNextLine())
             this.companyName = scanner.nextLine();
    }
    @Override
    void changeParameter(String parameter) {
        switch (parameter.toLowerCase()){
            case "organization name":
                setCompanyName();
                break;
            case "address":
                setAddress();
                break;
            case "number":
                super.setNumber(this.scanner);
                break;
            default:
                break;
        }
        super.setLastTimeEdited();
        System.out.println("The record updated!");
    }

    @Override
    String getShortName() {
        return getCompanyName();
    }

    @Override
    public String toString() {
        return "Organization name: " + companyName + '\n' +
                "Address: " + address + '\n' +
                super.toString();
    }
}


