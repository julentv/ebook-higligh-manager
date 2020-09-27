package julentv.books.higligh.migration;

import com.google.api.services.books.model.Volume;
import julentv.books.provider.google.CredentialFactory;
import julentv.books.provider.google.books.GoogleBooksManagerFactory;
import julentv.books.provider.google.books.id.GoogleBookIdProvider;

public class Main {
    public static void main(String[] args) {
        GoogleBookIdProvider provider = new GoogleBookIdProvider(new GoogleBooksManagerFactory(new CredentialFactory()));
        Volume elJuegoDeEnder = provider.getIdFromTitle("El juego de Ender");
        System.out.println(elJuegoDeEnder.getId());
    }
}
