package julentv.books.provider.google.books;

import com.google.api.services.books.Books;
import com.google.api.services.books.model.Volume;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GoogleBooksManager {
    public static final long RESULTS_PER_LOOP = 50;
    private final Books books;

    public GoogleBooksManager(Books books) {
        this.books = books;
    }

    public List<Volume> getAllVolumes() {
        try {
            return getAllVolumesThrowingExceptions();
        } catch (IOException e) {
            throw new RuntimeException("Error getting volumes from google");
        }
    }

    private List<Volume> getAllVolumesThrowingExceptions() throws IOException {
        List<Volume> allVolumes = new ArrayList<>();
        long index = 0;
        Books.Mylibrary.Bookshelves.Volumes.List volumesList = books.mylibrary().bookshelves().volumes().list("7");
        Optional<List<Volume>> optionalCurrentVolumes;
        do {
            optionalCurrentVolumes = Optional.ofNullable(volumesList.setStartIndex(index)
                    .setMaxResults(RESULTS_PER_LOOP).execute().getItems());
            optionalCurrentVolumes.ifPresent(allVolumes::addAll);
            index += RESULTS_PER_LOOP;
        } while (optionalCurrentVolumes.isPresent() && optionalCurrentVolumes.get().size() == RESULTS_PER_LOOP);
        return allVolumes;
    }
}
