package contacts;
import com.sun.jdi.Value;
import util.Input;

import java.awt.*;
import java.security.Key;
import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class contacts {
 //   ArrayList<String> = new ArrayList<String>();
    static Input input = new Input();
    static String directory = "src/contacts";
    static String filename = "contacts.txt";

    public static void main(String[] args) {
        start();

    }

    public static void start() {

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
//Search a contact by name
        if(selection == 3){
            try{
                toMap(L);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
//Deletes a contact
        if(selection == 4){
            try{
                deleteItem();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
//Exits the program
        if(selection == 5){
            System.out.println("Bye");
            System.exit(0);
        }

    }

    public static void showItems() throws IOException {
        // We use Paths.get to get a Java Path object
        Path contactsPath = Paths.get("src/contacts", "contacts.txt");
        // if the file does not exist, then create it
        if(Files.notExists(contactsPath)) {
            Files.createFile(contactsPath);
        }
        // .readAllLines returns a List type
        List<String> Contact = Files.readAllLines(contactsPath);

        System.out.println("Name | Phone Number");
        System.out.println("-------------------");

        for(String name : Contact) {
            System.out.println(name);
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
        List<String> Contact = Files.readAllLines(Paths.get(directory, filename));

        String name = input.getString("Please enter a new contact:");
        String phoneNumber = input.getString("Please enter a phone number:");
//        String number = scan.getString();
        phoneNumber = "(" + phoneNumber.substring( 0,3 ) + ") " + phoneNumber.substring( 3,6 ) + "-" + phoneNumber.substring( 6,10 );

        Contact.add(name + " | " + phoneNumber);

        Files.write(contactsPath, Contact);

        menu();
    }

    public static void toMap() throws IOException{
        List<String> Contact = new ArrayList<>(Files.readAllLines(Paths.get(directory, filename)));
        HashMap<String, String> ContactMap = new HashMap<>();
        for(int i = 0; i < Contact.size(); i++){
            ContactMap.put(String.valueOf(i), Contact.get(i));
        }
        System.out.println(ContactMap);
    }


    public static void deleteItem() throws IOException {

        Path contactsPath = Paths.get(directory, filename);

        if(Files.notExists(contactsPath)) {
            Files.createFile(contactsPath);
        }

       List<String> Contact = Files.readAllLines(Paths.get(directory, filename));
        HashMap<String, String> ContactMap = new HashMap<>();
        for(int i = 0; i < Contact.size(); i++){
            ContactMap.put(String.valueOf(i), Contact.get(i));
        }
        String name = input.getString("Please enter a contact to remove:");

     if(ContactMap.containsKey(name)) {
         ContactMap.remove(name);
         System.out.println("Contact Removed");
         Contact = new ArrayList<>(ContactMap.keySet());
          Contact = new ArrayList<>(ContactMap.values());
     }
     else {
         System.out.println("Invalid contact name");
     }
        Files.write(contactsPath, Contact);

        menu();
    }
}