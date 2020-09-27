package julentv.books.provider.google.books.id;

import com.google.api.services.books.model.Volume;
import julentv.books.provider.google.books.GoogleBooksManager;
import julentv.books.provider.google.books.GoogleBooksManagerFactory;

import java.util.List;

public class GoogleBookIdProvider {
    private final GoogleBooksManagerFactory googleBooksManagerFactory;

    public GoogleBookIdProvider(GoogleBooksManagerFactory googleBooksManagerFactory) {
        this.googleBooksManagerFactory = googleBooksManagerFactory;
    }

    public Volume getIdFromTitle(String bookTitle) {
        try {
            GoogleBooksManager googleBooksManager = googleBooksManagerFactory.createGoogleBooksManager();
            return getBookId(googleBooksManager, bookTitle);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Volume getBookId(GoogleBooksManager googleBooksManager, String bookTitle) {
        List<Volume> volumes = googleBooksManager.getAllVolumes();
        return volumes.stream().filter(volume -> volume.getVolumeInfo().getTitle().equals(bookTitle)).findAny()
                .orElseThrow(() -> new NullPointerException("No id found for " + bookTitle));
    }
}
