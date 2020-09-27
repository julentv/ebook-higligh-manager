package julentv.books.cervantes;

import julentv.books.Book;
import julentv.books.cervantes.extraction.CervantesXmlBookExtractor;
import julentv.books.highlight.extraction.cervantes.CervantesXmlHighlightExtractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;

class CervantesBookProviderTest {
    @Mock
    private CervantesXmlBookExtractor bookExtractor;
    @Mock
    private CervantesXmlHighlightExtractor highlightExtractor;
    @Mock
    private File mockFile;
    private CervantesBookProvider provider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        provider = new CervantesBookProvider(bookExtractor, highlightExtractor);
    }

    @Test
    void getBook() {
        Book book = provider.getBook(mockFile);
        Assertions.assertEquals(book.getMetadata().getTitle(), "test Title");
        Assertions.assertEquals(book.getMetadata().getAuthor(), "test Author");
    }
}