package julentv.books;

import java.io.File;

public interface BookProvider {
    Book getBook(File file);
}
