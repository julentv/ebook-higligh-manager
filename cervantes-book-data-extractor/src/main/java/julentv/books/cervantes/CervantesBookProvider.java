package julentv.books.cervantes;

import julentv.books.Book;
import julentv.books.BookProvider;
import julentv.books.cervantes.extraction.CervantesXmlBookExtractor;
import julentv.books.highlight.extraction.cervantes.CervantesXmlHighlightExtractor;

import java.io.File;

public class CervantesBookProvider implements BookProvider {
    private final CervantesXmlBookExtractor bookExtractor;
    private final CervantesXmlHighlightExtractor highlightExtractor;

    public CervantesBookProvider(CervantesXmlBookExtractor bookExtractor, CervantesXmlHighlightExtractor highlightExtractor) {
        this.bookExtractor = bookExtractor;
        this.highlightExtractor = highlightExtractor;
    }

    @Override
    public Book getBook(File file) {
        return null;
    }
}
