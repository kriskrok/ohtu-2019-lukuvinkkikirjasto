package lukuvinkkikirjasto.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BookTest {

    private Book book;

    @Before
    public void createANewBook() {
        this.book = new Book("Kirja", "Kalle");
    }

    @Test
    public void aValidNameCanBeSet() {
        assertEquals(book.getBooktitle(), "Kirja");
    }

    @Test
    public void aValidWriterCanBeSet() {
        assertEquals(book.getWriter(), "Kalle");
    }

    @Test
    public void theMethodToStringWorks() {
        assertEquals(book.toString(), "Kirja - Kalle");
    }
}