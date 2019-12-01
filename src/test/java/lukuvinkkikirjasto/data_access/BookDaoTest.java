package lukuvinkkikirjasto.domain;

import lukuvinkkikirjasto.domain.*;
import lukuvinkkikirjasto.utilities.*;
import lukuvinkkikirjasto.data_access.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class BookDaoTest {

    private BookDao dao;
    private List<Book> books;

    @Before
    public void createNewDao() throws Exception {
        this.dao = new BookDao(new Database());
        this.books = new ArrayList<>();
    }

/*  @Test
    public void aNewBookWithValidInputsCanBeAdded() throws Exception {
        dao.newBook("TestName", "TestWriter", 2);
        this.books = dao.getBooks();
        assertEquals(1, this.books.size());
    }
*/
}