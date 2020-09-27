package julentv.books.provider.google.books;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.books.Books;
import julentv.books.provider.google.CredentialFactory;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class GoogleBooksManagerFactory {
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    private final CredentialFactory credentialFactory;

    public GoogleBooksManagerFactory(CredentialFactory credentialFactory) {
        this.credentialFactory = credentialFactory;
    }

    public GoogleBooksManager createGoogleBooksManager() {
        try {
            Credential credential = credentialFactory.createCredential();
            return new GoogleBooksManager(getGoogleBooks(credential));
        } catch (GeneralSecurityException | IOException e) {
            throw new GoogleBooksManagerCreatingException(e);
        }
    }

    private Books getGoogleBooks(Credential credential) throws GeneralSecurityException, IOException {
        return new Books.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, null)
                .setHttpRequestInitializer(credential)
                .build();
    }
}
