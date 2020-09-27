package julentv.books.provider.google;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.books.BooksScopes;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collections;

public class CredentialFactory {
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final java.io.File DATA_STORE_DIR = new java.io.File("C:\\datastore");
    private static final String CLIENT_SECRETS_FILE = "secrets.json";

    public Credential createCredential() {
        try {
            GoogleClientSecrets clientSecrets = loadClientSecrets();
            FileDataStoreFactory fileDataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);
            GoogleAuthorizationCodeFlow flow = buildGoogleAuthorizationCodeFlow(clientSecrets, fileDataStoreFactory);
            return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
        } catch (IOException e) {
            throw new CredentialCreatingException(e);
        }
    }

    private GoogleAuthorizationCodeFlow buildGoogleAuthorizationCodeFlow(GoogleClientSecrets clientSecrets, FileDataStoreFactory fileDataStoreFactory) throws IOException {
        return new GoogleAuthorizationCodeFlow.Builder(new NetHttpTransport(), JSON_FACTORY, clientSecrets, Collections.singleton(BooksScopes.BOOKS))
                .setDataStoreFactory(fileDataStoreFactory)
                .setApprovalPrompt("force")
                .build();
    }

    private GoogleClientSecrets loadClientSecrets() throws IOException {
        Reader in = new FileReader(CredentialFactory.class.getResource(CLIENT_SECRETS_FILE).getFile());
        return GoogleClientSecrets.load(JSON_FACTORY, in);
    }
}
