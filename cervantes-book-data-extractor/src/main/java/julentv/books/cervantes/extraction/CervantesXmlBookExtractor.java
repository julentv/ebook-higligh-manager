package julentv.books.cervantes.extraction;

import julentv.books.cervantes.Books;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;

public class CervantesXmlBookExtractor {
    public Books getBooks(File file) {
        try {
            var jaxbContext = JAXBContext.newInstance(Books.class);
            var jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (Books) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            throw new BooksExtractingException(e);
        }

    }
}
