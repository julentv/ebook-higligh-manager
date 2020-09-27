package julentv.books.cervantes;

import julentv.books.Book;
import julentv.books.BookMetadata;
import julentv.books.provider.BookProvider;
import julentv.books.highlight.Highlight;
import julentv.books.highlight.extraction.cervantes.CervantesXmlHighlightExtractor;

import java.io.File;
import java.util.List;

public class CervantesBookProvider implements BookProvider {
    private final Books books;
    private final CervantesXmlHighlightExtractor highlightExtractor;

    public CervantesBookProvider(Books books, CervantesXmlHighlightExtractor highlightExtractor) {
        this.books = books;
        this.highlightExtractor = highlightExtractor;
    }

    @Override
    public Book getBook(File notesFile) {
        List<Highlight> highlights = highlightExtractor.getHighlights(notesFile);
        BookMetadata metadata = getCervantesBookMetadata(notesFile);
        return new Book(metadata, highlights);
    }

    private BookMetadata getCervantesBookMetadata(File notesFile) {
        julentv.books.cervantes.Book cervantesMetadata = books.getBooks().stream()
                .filter(book -> book.getFileName().equals(notesFile.getName()))
                .findFirst()
                .orElseThrow();
        return new BookMetadata(cervantesMetadata.getTitle(), cervantesMetadata.getAuthor());
    }
}
