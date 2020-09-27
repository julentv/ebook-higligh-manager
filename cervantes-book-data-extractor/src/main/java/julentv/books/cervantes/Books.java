package julentv.books.cervantes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Books {
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    @XmlElements(value = { @XmlElement(name="book") })
    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
