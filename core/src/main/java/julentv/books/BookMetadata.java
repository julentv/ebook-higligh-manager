package julentv.books;

public class BookMetadata {
    private final String title;
    private final String author;

    public BookMetadata(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}
