package julentv.books;

import julentv.books.highlight.Highlight;

import java.util.List;

public class Book {
    private final BookMetadata metadata;
    private final List<Highlight> highlights;

    public Book(BookMetadata metadata, List<Highlight> highlights) {
        this.metadata = metadata;
        this.highlights = highlights;
    }

    public BookMetadata getMetadata() {
        return metadata;
    }

    public List<Highlight> getHighlights() {
        return highlights;
    }
}
