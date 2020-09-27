package julentv.books.highlight.extraction.cervantes.handler;

import julentv.books.highlight.Highlight;

import java.util.Optional;

class HighlightBuilder {
    private static final String TYPE_PREFIX = "Type: ";
    private static final String HIGHLIGHT_TYPE = "HIGHLIGHT";
    private static final String TEXT_PREFIX = "Text: ";
    private static final String CHAPTER_PREFIX = "Chapter: ";

    private boolean isHighlight = false;
    private String content;
    private String chapter;

    HighlightBuilder addValueIfValid(String data) {
        var valueAccepted = processTypeParagraphIfMatches(data)
                || processTextParagraphIfMatches(data)
                || processChapterParagraphIfMatches(data);
        return this;
    }

    Optional<Highlight> build() {
        if (isHighlight) {
            return Optional.of(new Highlight(content, chapter));
        }
        return Optional.empty();
    }

    private boolean processChapterParagraphIfMatches(String value) {
        return processParagraphByTextStart(CHAPTER_PREFIX, value, () -> chapter = value.replace(CHAPTER_PREFIX, ""));
    }

    private boolean processTextParagraphIfMatches(String value) {
        return processParagraphByTextStart(TEXT_PREFIX, value, () -> content = value.replace(TEXT_PREFIX, ""));
    }

    private boolean processTypeParagraphIfMatches(String value) {
        return processParagraphByTextStart(TYPE_PREFIX, value, () -> {
            String type = value.replace(TYPE_PREFIX, "");
            if (type.equalsIgnoreCase(HIGHLIGHT_TYPE)) {
                isHighlight = true;
            }
        });
    }

    private boolean processParagraphByTextStart(String text, String value, Runnable runnable) {
        if (value.startsWith(text)) {
            runnable.run();
            return true;
        }
        return false;
    }
}
