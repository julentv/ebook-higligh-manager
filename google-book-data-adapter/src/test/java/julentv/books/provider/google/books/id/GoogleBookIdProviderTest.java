package julentv.books.provider.google.books.id;

import com.google.api.services.books.model.Volume;
import julentv.books.provider.google.books.GoogleBooksManager;
import julentv.books.provider.google.books.GoogleBooksManagerFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.mockito.Mockito.when;

class GoogleBookIdProviderTest {
    @Mock
    private GoogleBooksManagerFactory factory;
    @Mock
    private GoogleBooksManager googleBooksManager;
    private GoogleBookIdProvider googleBookIdProvider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        googleBookIdProvider = new GoogleBookIdProvider(factory);
    }

    @Test
    void getIdFromTitle() {
        when(factory.createGoogleBooksManager()).thenReturn(googleBooksManager);
        when(googleBooksManager.getAllVolumes()).thenReturn(Arrays.asList(createVolume("someTitle", "someId"),
                createVolume("otherTitle", "otherId")));

        Assertions.assertEquals("someId", googleBookIdProvider.getIdFromTitle("someTitle").getId());

    }

    private Volume createVolume(String title, String id) {
        Volume volume = new Volume();
        volume.setId(id);
        Volume.VolumeInfo volumeInfo = new Volume.VolumeInfo();
        volumeInfo.setTitle(title);
        volume.setVolumeInfo(volumeInfo);
        return volume;
    }
}