
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

    public List<Book> getBooks() {
        return books;
    }

    public void newBook(String name, String writer) {
        books.add(new Book(name, writer, generateId()));
    }

    public void deleteBook(String id) {

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