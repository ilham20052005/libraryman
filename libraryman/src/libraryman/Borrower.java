package libraryman;

import java.util.ArrayList;
import java.util.List;

public class Borrower {
    private String name;
    private String universityId;
    private List<Book> borrowedBooks;

    public Borrower(String name, String universityId) {
        this.name = name;
        this.universityId = universityId;
        this.borrowedBooks = new ArrayList<>();
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        book.borrow();
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
        book.returnBook();
    }

    public String getName() {
        return name;
    }

    public String getUniversityId() {
        return universityId;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
}
