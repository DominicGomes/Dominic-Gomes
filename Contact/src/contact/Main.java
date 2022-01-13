package contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Contacts> contactsList;
    private static Scanner scanner;
    private static int id;

    public static void main(String[] args) {
        contactsList = new ArrayList<>();
        System.out.println("\nGreetings...\n");
        showOptions();
    }

    private static void showOptions() {
        System.out.println("Select one: " + "\n\t1. Manage Contacts" + "\n\t2. Messages" + "\n\t3. Quit");
        scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                manageContacts();
                break;
            case 2:
                manageMessages();
                break;
            default:
                System.out.println("Program closing....");
                break;
        }
    }

    private static void manageContacts() {
        System.out.println("Select one: " +
                "\n\t1. Show all contacts" +
                "\n\t2. Add a new contact" +
                "\n\t3. Search a contact" +
                "\n\t4. Delete a contact" +
                "\n\t5. Previous menu");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> showContacts();
            case 2 -> newContact();
            case 3 -> searchContact();
            case 4 -> deleteContact();
            case 5 -> showOptions();
            default -> {
                System.out.println("Input a right number");
                manageContacts();
            }
        }
    }

    private static void showContacts() {
        if (contactsList.size()>0){
            for (Contacts c : contactsList) {
                c.getDetails();
                System.out.println("******");
            }

        }else {
            System.out.println("No contacts exists");
        }
        showOptions();
    }

    private static void newContact() {

        System.out.println("Enter a name: ");
        String name = scanner.next();
        System.out.println("Enter number");
        String number = scanner.next();
        System.out.println("Enter email");
        String email = scanner.next();

        if (name.equals("") || number.equals("") || email.equals("")) {
            System.out.println("Fields can not be empty");
            newContact();
        } else {
            boolean doesExist = false;
            for (Contacts c : contactsList) {
                if (c.getName().equals(name)) {
                    doesExist = true;
                }
            }
            if (doesExist) {
                System.out.println("Contact already exists");
                newContact();
            } else {
                System.out.println(name + " has been added to contacts");
                Contacts contacts = new Contacts(name, number, email);
                contactsList.add(contacts);
            }
        }
        showOptions();
    }

    private static void searchContact() {
        System.out.println("Enter the name:");
        String name = scanner.next();
        if (name.equals("")) {
            System.out.println("Field can not be empty");
            searchContact();
        } else {
            boolean found = false;
            for (Contacts c : contactsList) {
                if (c.getName().equals(name)) {
                    found = true;
                    c.getDetails();
                }

            }
            if (!found) {
                System.out.println("Contact not found");
            }

        }
        showOptions();
    }

    private static void deleteContact() {
        System.out.println("Enter the name:");
        String name = scanner.next();
        boolean doesExists = false;
        for (Contacts c: contactsList){
            if (c.getName().equals(name)){
                doesExists = true;
                contactsList.remove(name);
                System.out.println(name+" has been deleted from contacts");
            }

        } if (!doesExists){
            System.out.println("Contact not found!");
        }
        showOptions();
    }

    private static void manageMessages() {
        System.out.println("Select one: "+
                "\t\n1. Show all messages"+
                "\t\n2. Send a message"+
                "\t\n3. Previous menu");

        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                showMessages();
                break;
            case 2:
                sendMessage();
                break;
            default:
                showOptions();
        }
    }

    private static void showMessages() {
       List<Message> messageList = new ArrayList<>();
        for (Contacts c: contactsList) {
            messageList.addAll(c.getMessages());
        }
        if (messageList.size()>0){
            for (Message m: messageList) {
                m.getDetails();
                System.out.println("******");
            }
        }else {
            System.out.println("You do not have any messages");
        }
        showOptions();

    }
    private static void sendMessage() {
        System.out.println("Enter Recipient name: ");
        String name = scanner.next();

        if (name.equals("")){
            System.out.println("Please enter a name");
            sendMessage();
        }else {
            boolean doesExist = false;
            for (Contacts c: contactsList){
                if (c.getName().equals(name)){
                    doesExist = true;
                }
            }
            if (doesExist){
                System.out.println("Type your message: ");
                String text = scanner.next();
                if (text.equals("")){
                    System.out.println("Enter your text");
                    sendMessage();
                }else {
                    id++;
                    Message message = new Message(name,text,id);
                    for (Contacts c:contactsList) {
                        if (c.getName().equals(name)){
                            List<Message> newMessages = c.getMessages();
                            newMessages.add(message);
                            c.setMessages(newMessages);
                        }
                    }
                }

            }else {
                System.out.println("Contact doesn't exists");
            }
        }
        showOptions();

    }
}
