package libraryman;

public class PaperBook extends Book {
    public PaperBook(String title, String author, String isbn) {
        super(title, author, isbn);
    }

    @Override
    public String getType() {
        return "Paper Book";
    }
}
