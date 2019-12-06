package lukuvinkkikirjasto.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BookTest {

    private Book book;

    @Before
    public void createANewBook() {
        this.book = new Book("Kirja", "Kalle", 1);
    }

    @Test
    public void aValidNameCanBeSet() {
        assertEquals(book.getBooktitle(), "Kirja");
    }

    @Test
    public void aValidAuthorCanBeSet() {
        assertEquals(book.getAuthor(), "Kalle");
    }

    @Test
    public void theMethodToStringWorks() {
        assertEquals(book.toString(), "Kirja - Kalle");
    }
}