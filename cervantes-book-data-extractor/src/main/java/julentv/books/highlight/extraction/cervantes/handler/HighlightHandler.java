package julentv.books.highlight.extraction.cervantes.handler;

import julentv.books.highlight.Highlight;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class HighlightHandler extends DefaultHandler {
    public static final String HTML_DIV = "div";
    public static final String HTML_PARAGRAPH = "p";

    private StringBuilder data = null;
    private final List<Highlight> highlights = new ArrayList<>();
    private HighlightBuilder highlightBuilder;

    public HighlightHandler() {
        highlightBuilder = new HighlightBuilder();
    }

    public List<Highlight> getHighlights() {
        return highlights;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (HTML_PARAGRAPH.equalsIgnoreCase(qName)) {
            processEndOfParagraph();
        } else if (HTML_DIV.equalsIgnoreCase(qName)) {
            processEndOfDiv();
        }
    }

    @Override
    public void characters(char[] chars, int start, int length) {
        data.append(new String(chars, start, length));
    }

    private void processEndOfDiv() {
        highlightBuilder.build().ifPresent(highlights::add);
        restartState();
    }

    private void processEndOfParagraph() {
        highlightBuilder.addValueIfValid(data.toString());
    }

    private void restartState() {
        highlightBuilder = new HighlightBuilder();
    }
}
