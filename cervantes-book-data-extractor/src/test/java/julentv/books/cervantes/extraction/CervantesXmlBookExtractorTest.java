package julentv.books.cervantes.extraction;

import julentv.books.cervantes.Books;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

class CervantesXmlBookExtractorTest {
    public static final URL XML_FILE_PATH = CervantesXmlBookExtractorTest.class.getResource("reduced-data.xml");
    public static final URL TXT_FILE_PATH = CervantesXmlBookExtractorTest.class.getResource("invalid-file.xml");

    @Test
    void getBooks() throws URISyntaxException {
        Books books = new CervantesXmlBookExtractor().getBooks(new File(XML_FILE_PATH.toURI()));

        Assertions.assertEquals(books.getBooks().size(), 3);

        Assertions.assertEquals(books.getBooks().get(0).getPath(), "/mnt/public/epl/AA. VV. & Beck, Aaron Temkin - Terapia cognitiva de los trastornos de personalidad [18688] (r1.0 marcelo77).epub");
        Assertions.assertEquals(books.getBooks().get(0).getFileName(), "AA. VV. & Beck, Aaron Temkin - Terapia cognitiva de los trastornos de personalidad [18688] (r1.0 marcelo77).epub");
        Assertions.assertEquals(books.getBooks().get(0).getAuthor(), "AA. VV.");
        Assertions.assertEquals(books.getBooks().get(0).getTitle(), "Terapia cognitiva de los trastornos de personalidad");

        Assertions.assertEquals(books.getBooks().get(1).getPath(), "/mnt/public/epl/misAportes/Lord Moran - Anatomia del valor [46984] (r1.1).epub");
        Assertions.assertEquals(books.getBooks().get(1).getFileName(), "Lord Moran - Anatomia del valor [46984] (r1.1).epub");
        Assertions.assertEquals(books.getBooks().get(1).getAuthor(), "Lord Moran");
        Assertions.assertEquals(books.getBooks().get(1).getTitle(), "Anatomía del valor");

        Assertions.assertEquals(books.getBooks().get(2).getPath(), "/mnt/public/epl/misAportes/Rekarte, Inaki - Lo dificil es perdonarse a uno mismo [23826] (r1.0).epub");
        Assertions.assertEquals(books.getBooks().get(2).getFileName(), "Rekarte, Inaki - Lo dificil es perdonarse a uno mismo [23826] (r1.0).epub");
        Assertions.assertEquals(books.getBooks().get(2).getAuthor(), "Iñaki Rekarte");
        Assertions.assertEquals(books.getBooks().get(2).getTitle(), "Lo difícil es perdonarse a uno mismo");
    }

    @Test
    void nonValidFile() {
        Assertions.assertThrows(BooksExtractingException.class, () -> {
            CervantesXmlBookExtractor extractor = new CervantesXmlBookExtractor();
            extractor.getBooks(new File(TXT_FILE_PATH.toURI()));
        });
    }
}