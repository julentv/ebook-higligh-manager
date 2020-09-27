package julentv.books.highlight.extraction.cervantes;

import julentv.books.highlight.Highlight;
import julentv.books.highlight.extraction.HighlightExtractor;
import julentv.books.highlight.extraction.cervantes.handler.HighlightHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CervantesXmlHighlightExtractor implements HighlightExtractor {

    public List<Highlight> getHighlights(File file) {
        try {
            HighlightHandler handler = createHighlightHandler();
            createParser().parse(createInputSource(file), handler);
            return handler.getHighlights();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new HighlightExtractionException(e);
        }
    }

    private HighlightHandler createHighlightHandler() {
        return new HighlightHandler();
    }

    private InputSource createInputSource(File file) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(file);
        Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

        InputSource is = new InputSource(reader);
        is.setEncoding("UTF-8");
        return is;
    }

    private SAXParser createParser() throws ParserConfigurationException, SAXException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        return saxParserFactory.newSAXParser();
    }
}
