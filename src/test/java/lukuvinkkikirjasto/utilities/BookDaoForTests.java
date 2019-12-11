
package lukuvinkkikirjasto.utilities;

import lukuvinkkikirjasto.domain.*;
import lukuvinkkikirjasto.utilities.*;
import lukuvinkkikirjasto.data_access.*;

import java.util.ArrayList;
import java.util.List;

public class BookDaoForTests extends BookDao {

    int maxId = 1;
    private List<Book> books;

    public BookDaoForTests() throws Exception {
        super(null);
        this.books = new ArrayList<>();
    }

    @Override
    public List<Lukuvinkki> findAll() {
        return new ArrayList<Lukuvinkki>(books);
    }

    public Book findByLukuvinkkiId(String lukuvinkkiID) {
        return books.stream().filter(book -> book.id == Integer.parseInt(lukuvinkkiID)).findFirst().orElseGet(null);
    }

    @Override
    public void insert(String name, String author) {
        books.add(new Book(name, author, generateId()));
    }

    @Override
    public void delete(String lukuvinkkiId) {
        Book bk = books.stream().filter(book -> book.id == Integer.parseInt(lukuvinkkiId)).findFirst().orElseGet(null);
        books.remove(bk);

    }

    private int generateId() {
        this.maxId++;
        return maxId;
    }

    private Book findByTitle(String title) {
        for (Book book : books) {
            if (book.getBooktitle().equals(title)) {
                return book;
            }
        }
        return null;
    }


}