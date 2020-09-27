package julentv.books.cervantes;

import javax.xml.bind.annotation.XmlElement;

public class Book {
    private String title;
    private String author;
    private String path;
    private String fileName;

    public String getTitle() {
        return title;
    }

    @XmlElement
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    @XmlElement
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPath() {
        return path;
    }

    @XmlElement
    public void setPath(String path) {
        this.path = path;
        String[] splittedPaths = path.split("/");
        fileName = splittedPaths[splittedPaths.length - 1];
    }

    public String getFileName() {
        return fileName;
    }
}
