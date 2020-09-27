package julentv.books.highlight;

public class Highlight {
    private final String text;
    private final String chapter;

    public Highlight(String text, String chapter) {
        this.text = text;
        this.chapter = chapter;
    }

    public String getText() {
        return text;
    }

    public String getChapter() {
        return chapter;
    }
}
