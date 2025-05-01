package libraryman;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {
    static List<Book> books = new ArrayList<>();
    static List<Borrower> borrowers = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\nLibrary Menu:");// NOSONAR
            System.out.println("1. Add Book");// NOSONAR
            System.out.println("2. Add Borrower");// NOSONAR
            System.out.println("3. Borrow Book");// NOSONAR
            System.out.println("4. Return Book");// NOSONAR
            System.out.println("5. Search Book");// NOSONAR
            System.out.println("6. Show Borrowed Books");// NOSONAR
            System.out.println("7. Exit");// NOSONAR
            System.out.println("Enter choice: ");// NOSONAR
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addBook();
                case 2 -> addBorrower();
                case 3 -> borrowBook();
                case 4 -> returnBook();
                case 5 -> searchBook();
                case 6 -> showBorrowedBooks();
                case 0 -> running = false;
                default -> System.out.println("Invalid choice.");// NOSONAR
            }
        }
    }

    private static void addBook() {
        System.out.println("Enter book type (1- Paper, 2- EBook): ");// NOSONAR
        int type = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter title: ");// NOSONAR
        String title = scanner.nextLine();
        System.out.println("Enter author: ");// NOSONAR
        String author = scanner.nextLine();
        System.out.println("Enter ISBN: ");// NOSONAR
        String isbn = scanner.nextLine();

        if (type == 1) {
            books.add(new PaperBook(title, author, isbn));
        } else {
            books.add(new EBook(title, author, isbn));
        }
        System.out.println("Book added successfully.");// NOSONAR
    }

    private static void addBorrower() {
        System.out.println("Enter name: ");// NOSONAR
        String name = scanner.nextLine();
        System.out.println("Enter university ID: ");// NOSONAR
        String id = scanner.nextLine();
        borrowers.add(new Borrower(name, id));
        System.out.println("Borrower added successfully.");// NOSONAR
    }

    private static void borrowBook() {
        System.out.println("Enter borrower university ID: ");// NOSONAR
        String id = scanner.nextLine();
        Borrower borrower = findBorrowerById(id);

        if (borrower == null) {
            System.out.println("Borrower not found.");// NOSONAR
            return;
        }

        System.out.println("Enter book ISBN: ");// NOSONAR
        String isbn = scanner.nextLine();
        Book book = findBookByIsbn(isbn);

        if (book == null || book.isBorrowed()) {
            System.out.println("Book not available.");// NOSONAR
            return;
        }

        borrower.borrowBook(book);
        System.out.println("Book borrowed successfully.");// NOSONAR
    }

    private static void returnBook() {
        System.out.println("Enter borrower university ID: ");// NOSONAR
        String id = scanner.nextLine();
        Borrower borrower = findBorrowerById(id);

        if (borrower == null) {
            System.out.println("Borrower not found.");// NOSONAR
            return;
        }

        System.out.println("Enter book ISBN: ");// NOSONAR
        String isbn = scanner.nextLine();
        Book book = findBookByIsbn(isbn);

        if (book != null && borrower.getBorrowedBooks().contains(book)) {
            borrower.returnBook(book);
            System.out.println("Book returned successfully.");// NOSONAR
        } else {
            System.out.println("Borrowed book not found.");// NOSONAR
        }
    }

    private static void searchBook() {
        System.out.println("Enter book title: ");// NOSONAR
        String title = scanner.nextLine();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Found: " + book.getTitle() + " by " + book.getAuthor() + " [" + book.getType() + "]");// NOSONAR
                return;
            }
        }
        System.out.println("Book not found.");// NOSONAR
    }

    private static void showBorrowedBooks() {
        System.out.println("Enter borrower university ID: ");// NOSONAR
        String id = scanner.nextLine();
        Borrower borrower = findBorrowerById(id);

        if (borrower != null) {
            System.out.println(borrower.getName() + "'s Borrowed Books:");// NOSONAR
            for (Book book : borrower.getBorrowedBooks()) {
                System.out.println("- " + book.getTitle() + " (" + book.getType() + ")");// NOSONAR
            }
        } else {
            System.out.println("Borrower not found.");// NOSONAR
        }
    }

    private static Borrower findBorrowerById(String id) {
        for (Borrower b : borrowers) {
            if (b.getUniversityId().equals(id)) {
                return b;
            }
        }
        return null;
    }

    private static Book findBookByIsbn(String isbn) {
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) {
                return b;
            }
        }
        return null;
    }
}
