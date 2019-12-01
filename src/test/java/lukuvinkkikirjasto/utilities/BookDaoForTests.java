
package lukuvinkkikirjasto.utilities;

import lukuvinkkikirjasto.domain.*;
import lukuvinkkikirjasto.utilities.*;
import lukuvinkkikirjasto.data_access.*;

import java.util.ArrayList;
import java.util.List;

public class BookDaoForTests implements LukuvinkkiDao {

    int maxId = 1;
    private List<Book> books;

    public BookDaoForTests() {
        this.books = new ArrayList<>();
    }

    public List<Book> findAll() {
        return books;
    }

    public Book findByLukuvinkkiId(String lukuvinkkiID) {
        return books.stream().filter(book -> book.id == Integer.parseInt(lukuvinkkiID)).findFirst().orElseGet(null);
    }

    public void insert(String name, String writer) {
        books.add(new Book(name, writer, generateId()));
    }

    public void delete(String lukuvinkkiId) {

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