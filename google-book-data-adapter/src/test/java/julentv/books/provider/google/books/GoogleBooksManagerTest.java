package julentv.books.provider.google.books;

import com.google.api.services.books.Books;
import com.google.api.services.books.model.Volume;
import com.google.api.services.books.model.Volumes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class GoogleBooksManagerTest {

    @Mock
    private Books books;
    @Mock
    private Books.Mylibrary myLibrary;
    @Mock
    private Books.Mylibrary.Bookshelves bookshelves;
    @Mock
    private Books.Mylibrary.Bookshelves.Volumes volumes;
    @Mock
    private Books.Mylibrary.Bookshelves.Volumes.List list;
    @Mock
    private Books.Mylibrary.Bookshelves.Volumes.List emptyList;
    private GoogleBooksManager manager;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        manager = new GoogleBooksManager(books);

        when(books.mylibrary()).thenReturn(myLibrary);
        when(myLibrary.bookshelves()).thenReturn(bookshelves);
        when(bookshelves.volumes()).thenReturn(volumes);
        when(volumes.list("7")).thenReturn(list);
    }

    @Test
    void getAllVolumes() throws IOException {
        when(list.setStartIndex(any())).thenReturn(emptyList);
        when(list.setStartIndex(0L)).thenReturn(list);
        when(list.setStartIndex(10L)).thenReturn(list);
        when(list.setMaxResults(50L)).thenReturn(list);
        when(list.execute()).thenReturn(createBookVolumes());
        when(emptyList.setMaxResults(50L)).thenReturn(emptyList);
        when(emptyList.execute()).thenReturn(createEmptyBookVolumes());

        List<Volume> volumes = manager.getAllVolumes();

        Assertions.assertEquals(volumes.size(), 1);
    }

    @Test
    void getAllVolumesIoException() throws IOException {
        when(list.setStartIndex(any())).thenReturn(list);
        when(list.execute()).thenThrow(new IOException());

        Assertions.assertThrows(RuntimeException.class, () -> {
            manager.getAllVolumes();
        }, "Error getting volumes from google");
    }

    private Volumes createBookVolumes() {
        Volumes volumes = new Volumes();
        volumes.setItems(Collections.singletonList(new Volume()));
        return volumes;
    }

    private Volumes createEmptyBookVolumes() {
        Volumes volumes = new Volumes();
        volumes.setItems(Collections.emptyList());
        return volumes;
    }
}