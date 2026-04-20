import java.io.*;
import java.util.*;

class Book implements Serializable {
    int id;
    String title;
    String author;
    boolean issued;

    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.issued = false;
    }
}

class User implements Serializable {
    int id;
    String name;

    User(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

public class LibraryManagementSystem {

    static ArrayList<Book> books = new ArrayList<>();
    static ArrayList<User> users = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        loadBooks();

        int choice;

        do {
            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1 Add Book");
            System.out.println("2 View Books");
            System.out.println("3 Issue Book");
            System.out.println("4 Return Book");
            System.out.println("5 Save & Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addBook();
                    break;

                case 2:
                    viewBooks();
                    break;

                case 3:
                    issueBook();
                    break;

                case 4:
                    returnBook();
                    break;

                case 5:
                    saveBooks();
                    System.out.println("Data Saved. Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 5);
    }

    static void addBook() {

        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Author: ");
        String author = sc.nextLine();

        books.add(new Book(id, title, author));

        System.out.println("Book Added Successfully!");
    }

    static void viewBooks() {

        if (books.isEmpty()) {
            System.out.println("No books available");
            return;
        }

        System.out.println("\nBook List:");

        for (Book b : books) {
            System.out.println(
                    b.id + " | " + b.title + " | " + b.author +
                            " | Issued: " + b.issued);
        }
    }

    static void issueBook() {

        System.out.print("Enter Book ID to issue: ");
        int id = sc.nextInt();

        for (Book b : books) {

            if (b.id == id) {

                if (b.issued) {
                    System.out.println("Book already issued");
                } else {
                    b.issued = true;
                    System.out.println("Book issued successfully");
                }
                return;
            }
        }

        System.out.println("Book not found");
    }

    static void returnBook() {

        System.out.print("Enter Book ID to return: ");
        int id = sc.nextInt();

        for (Book b : books) {

            if (b.id == id) {

                if (!b.issued) {
                    System.out.println("Book was not issued");
                } else {
                    b.issued = false;
                    System.out.println("Book returned successfully");
                }
                return;
            }
        }

        System.out.println("Book not found");
    }

    static void saveBooks() {

        try {

            ObjectOutputStream oos =
                    new ObjectOutputStream(new FileOutputStream("books.dat"));

            oos.writeObject(books);
            oos.close();

        } catch (Exception e) {
            System.out.println("Error saving file");
        }
    }

    static void loadBooks() {

        try {

            ObjectInputStream ois =
                    new ObjectInputStream(new FileInputStream("books.dat"));

            books = (ArrayList<Book>) ois.readObject();

            ois.close();

        } catch (Exception e) {
            System.out.println("No previous data found");
        }
    }
}