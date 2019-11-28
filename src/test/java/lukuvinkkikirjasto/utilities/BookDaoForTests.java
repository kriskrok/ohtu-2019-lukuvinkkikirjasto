
package lukuvinkkikirjasto.utilities;

import lukuvinkkikirjasto.domain.*;
import lukuvinkkikirjasto.utilities.*;
import lukuvinkkikirjasto.data_access.*;

import java.util.ArrayList;
import java.util.List;

public class BookDaoForTests implements LukuvinkkiDao {

    private List<Kirja> books;

    public BookDaoForTests() {
        this.books = new ArrayList<>();
    }

    public List<Kirja> getBooks() {
        return books;
    }

    public void newBook(String name, String writer) {
        books.add(new Kirja(name, writer));
    }

    public void deleteBook(String id) {
    }

/*    private Kirja findByTitle(String title) {
        for (Kirja book : books) {
            if (book.getKirjanNimi().equals(title)) {
                return book;
            }
        }

        return null;
    }
*/


}