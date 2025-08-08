import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private int id;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return isAvailable; }

    public void borrowBook() { this.isAvailable = false; }
    public void returnBook() { this.isAvailable = true; }

    @Override
    public String toString() {
        return id + " | " + title + " | " + author + " | " + (isAvailable ? "Available" : "Borrowed");
    }
}

class Library {
    private List<Book> books;
    private int nextId = 1;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(String title, String author) {
        books.add(new Book(nextId++, title, author));
        System.out.println("Book added successfully!");
    }

    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        System.out.println("\nID | Title | Author | Status");
        System.out.println("---------------------------");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) return book;
        }
        return null;
    }

    public void borrowBook(int id) {
        Book book = findBookById(id);
        if (book != null && book.isAvailable()) {
            book.borrowBook();
            System.out.println(" Book borrowed successfully!");
        } else if (book != null) {
            System.out.println("Book is already borrowed.");
        } else {
            System.out.println(" Book not found.");
        }
    }

    public void returnBook(int id) {
        Book book = findBookById(id);
        if (book != null && !book.isAvailable()) {
            book.returnBook();
            System.out.println("Book returned successfully!");
        } else if (book != null) {
            System.out.println("Book was not borrowed.");
        } else {
            System.out.println(" Book not found.");
        }
    }

    public void removeBook(int id) {
        Book book = findBookById(id);
        if (book != null) {
            books.remove(book);
            System.out.println("Book removed successfully!");
        } else {
            System.out.println(" Book not found.");
        }
    }
}

public class LibraryManagementSystem {
    private static final Scanner sc = new Scanner(System.in);
    private static final Library library = new Library();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Remove Book");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = getIntInput();
            switch (choice) {
                case 1: addBook(); break;
                case 2: library.viewBooks(); break;
                case 3: borrowBook(); break;
                case 4: returnBook(); break;
                case 5: removeBook(); break;
                case 6: 
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addBook() {
        System.out.print("Enter book title: ");
        String title = sc.nextLine();
        System.out.print("Enter author: ");
        String author = sc.nextLine();
        library.addBook(title, author);
    }

    private static void borrowBook() {
        System.out.print("Enter book ID to borrow: ");
        int id = getIntInput();
        library.borrowBook(id);
    }

    private static void returnBook() {
        System.out.print("Enter book ID to return: ");
        int id = getIntInput();
        library.returnBook(id);
    }

    private static void removeBook() {
        System.out.print("Enter book ID to remove: ");
        int id = getIntInput();
        library.removeBook(id);
    }

    private static int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Enter a valid number: ");
            }
        }
    }
}
