
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

    public void insert(String name, String author) {
        books.add(new Book(name, author, generateId()));
    }

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

    @Override
    public void update(String lukuvinkkiId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}