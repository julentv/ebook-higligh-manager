package julentv.books.highlight.extraction.cervantes;

import julentv.books.highlight.Highlight;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

class CervantesXmlHighlightExtractorTest {
    public static final URL XML_FILE_PATH = CervantesXmlHighlightExtractorTest.class.getResource("Le Guin, Ursula K. - [Ciclo de Hainish 06] El nombre del mundo es bosque [17307] (r1.2 diegoan).epub.html");
    public static final URL TXT_FILE_PATH = CervantesXmlHighlightExtractorTest.class.getResource("textFile.txt");

    @Test
    void basicParsing() throws URISyntaxException {
        CervantesXmlHighlightExtractor extractor = new CervantesXmlHighlightExtractor();
        List<Highlight> highlights = extractor.getHighlights(new File(XML_FILE_PATH.toURI()));

        Assertions.assertEquals(highlights.size(), 2);
        Assertions.assertEquals(highlights.get(0).getText(), "A veces llega un dios -dijo Selver-. Trae una nueva forma de hacer una cosa, o una cosa nueva para hacer. Una nueva clase de canto, o una nueva clase de muerte. Lo trae a través del puente entre el tiempo-sueño y el tiempo-mundo. Y una vez que lo ha hecho, hecho está.\n" +
                "\n" +
                "  »Uno no puede tomar cosas del mundo y tratar de llevarlas al sueño, encerrarlas en el sueño con");
        Assertions.assertEquals(highlights.get(0).getChapter(), "test chapter1");

        Assertions.assertEquals(highlights.get(1).getText(), "muros y engaños. Eso es demencia. No pretenderé, ahora, que nosotros no sabemos cómo matarnos unos a otros");
        Assertions.assertEquals(highlights.get(1).getChapter(), "test chapter2");
    }

    @Test
    void nonValidFile() {
        Assertions.assertThrows(HighlightExtractionException.class, () -> {
            CervantesXmlHighlightExtractor extractor = new CervantesXmlHighlightExtractor();
            extractor.getHighlights(new File(TXT_FILE_PATH.toURI()));
        });
    }
}