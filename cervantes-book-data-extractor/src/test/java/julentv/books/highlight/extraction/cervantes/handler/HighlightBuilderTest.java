package julentv.books.highlight.extraction.cervantes.handler;

import julentv.books.highlight.Highlight;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class HighlightBuilderTest {

    @Test
    void buildHighlight() {
        var builder = new HighlightBuilder();
        var highlightOptional = builder.addValueIfValid("Type: HIGHLIGHT")
                .addValueIfValid("Text: some Text")
                .addValueIfValid("Chapter: some chapter")
                .build();

        Assertions.assertTrue(highlightOptional.isPresent());
        var highlight = highlightOptional.get();
        Assertions.assertEquals(highlight.getText(), "some Text");
        Assertions.assertEquals(highlight.getChapter(), "some chapter");
    }

    @Test
    void invalidHighlight() {
        var builder = new HighlightBuilder();
        var highlightOptional = builder.addValueIfValid("Text: some Text")
                .addValueIfValid("Chapter: some chapter")
                .build();
        Assertions.assertFalse(highlightOptional.isPresent());
    }
}