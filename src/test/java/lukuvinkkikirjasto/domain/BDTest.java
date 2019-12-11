
package lukuvinkkikirjasto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lukuvinkkikirjasto.utilities.BookDaoForTests;
import org.assertj.core.util.Arrays;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class BDTest {

    private BookDaoForTests dao;

    @Before
    public void setUp() throws Exception {
        dao = new BookDaoForTests();
        String name = "name";
        String author = "author";
        dao.insert(name, author);
    }

    @Test
    public void DaolistsAllBooks() {
        dao.findAll();
    }

    @Test
    public void DaoAddsBook() {
        int sizeNow = dao.findAll().size();
        String name = "name";
        String author = "author";
        dao.insert(name, author);
        assertEquals(dao.findAll().size(), sizeNow + 1);
    }

    @Test
    public void daoDeletesBookById() {
        List<Integer> ids = dao.findAll().stream().map(book -> book.id).collect(Collectors.toList());
        int id = ids.get(0);
        dao.delete(String.valueOf(id));
        assertEquals(dao.findAll().size(), 0);

    }

    @Test
    public void DaoFindsBookById() {
        String name = "name";
        String author = "author";
        dao.insert(name, author);
        List<Integer> ids = dao.findAll().stream().map(book -> book.id).collect(Collectors.toList());
        int id = ids.get(0);
        assertEquals(dao.findByLukuvinkkiId(String.valueOf(id)).toString(), "name - author");
    }

}
