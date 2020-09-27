package julentv.books.cervantes;

import julentv.books.Book;
import julentv.books.highlight.Highlight;
import julentv.books.highlight.extraction.cervantes.CervantesXmlHighlightExtractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

class CervantesBookProviderTest {
    @Mock
    private CervantesXmlHighlightExtractor highlightExtractor;
    @Mock
    private File highlightFile;
    @Mock
    private List<Highlight> highlights;
    private CervantesBookProvider provider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Books books = new Books();
        books.setBooks(Arrays.asList(createBook("book1", "path/book1.html", "author1"), createBook("book2", "path/book2.html", "author2")));
        provider = new CervantesBookProvider(books, highlightExtractor);
    }

    @Test
    void getBook() {
        when(highlightExtractor.getHighlights(highlightFile)).thenReturn(highlights);
        when(highlightFile.getName()).thenReturn("book1.html");

        Book book = provider.getBook(highlightFile);
        Assertions.assertEquals(book.getMetadata().getTitle(), "book1");
        Assertions.assertEquals(book.getMetadata().getAuthor(), "author1");
        Assertions.assertEquals(book.getHighlights(), highlights);
    }

    private julentv.books.cervantes.Book createBook(String title, String path, String author) {
        var book = new julentv.books.cervantes.Book();
        book.setPath(path);
        book.setTitle(title);
        book.setAuthor(author);
        return book;
    }
}