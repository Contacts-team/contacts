

package contacts;
import util.Input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;



public class contacts {
    static Input input = new Input();
    static String directory = "src/contacts";
    static String filename = "contacts.txt";

    public static void main(String[] args) {
        start();

    }

    public static void start() {
        System.out.println("");
        menu();
    }

    public static void menu() {

        System.out.println("Please select an option");
        System.out.println("1 - View contacts");
        System.out.println("2 - Add a new contact");
        System.out.println("3 - Search a contact by name");
        System.out.println("4 - Delete an existing contact");
        System.out.println("5 - Exit");
        System.out.println("Enter 1,2,3,4 or 5");
        int selection = input.getInt(1, 5);

        if(selection == 1) {

            try {
                showItems();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        //Adds contact if the user enters 2
        if(selection == 2) {

            try {
                addNewItem();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
//        else {
//            System.out.println("");
//            System.exit(0);
//        }

        if(selection == 4){
            try{
                deleteItem();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        if(selection == 5){
            System.out.println("");
           System.exit(0);
        }

    }

    public static void showItems() throws IOException {
        // We use Paths.get to get a Java Path object
        Path contactsPath = Paths.get("src/contacts", "contacts.txt");

        // .readAllLines returns a List type
        List<String> contacts = Files.readAllLines(contactsPath);

        System.out.println("Name | Phone Number");
        System.out.println("-------------------");

        for(String item : contacts) {
            System.out.println(item);
        }
        System.out.println();

        menu();

    }

    public static void addNewItem() throws IOException {

        // open the file and add a new item to the file
        Path contactsPath = Paths.get(directory, filename);

        // if the file does not exist, then create it
        if(Files.notExists(contactsPath)) {
            Files.createFile(contactsPath);
        }

//         assigns lines to hold all of the strings already in the file
        List<String> lines = Files.readAllLines(Paths.get(directory, filename));

        String item = input.getString("Please enter a new contact:");
        String phoneNumber = input.getString("Please enter a phone number:");

        lines.add(item + " | " + phoneNumber);

        Files.write(contactsPath, lines);

        menu();
    }

    public static void deleteItem() throws IOException {

        Path contactsPath = Paths.get(directory, filename);

        if(Files.notExists(contactsPath)) {
            Files.createFile(contactsPath);
        }

        List<String> lines = Files.readAllLines(Paths.get(directory, filename));

        String item = input.getString("Please enter a contact to remove:");

        lines.remove(item);

        Files.write(contactsPath, lines);

        menu();
    }
}