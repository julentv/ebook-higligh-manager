package julentv.books.highlight.extraction;

import julentv.books.highlight.Highlight;

import java.io.File;
import java.util.List;

public interface HighlightExtractor {
    List<Highlight> getHighlights(File file);
}
