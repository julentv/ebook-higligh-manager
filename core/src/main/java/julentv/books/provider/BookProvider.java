package julentv.books.provider;

import julentv.books.Book;

import java.io.File;

public interface BookProvider {
    Book getBook(File file);
}
