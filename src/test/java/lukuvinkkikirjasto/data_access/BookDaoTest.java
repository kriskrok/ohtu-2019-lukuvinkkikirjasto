package lukuvinkkikirjasto.data_access;

import lukuvinkkikirjasto.domain.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.*;
import java.io.File;

public class BookDaoTest {

    private BookDao bookDao;
    private Database database = new Database();
    int testiId;
    private Database mockDB = mock(Database.class);


    @Before
    public void createNewDao() throws Exception {
        this.bookDao = new BookDao(database);
    }

    public void setTestLukuvinkki() throws Exception {
        Connection conn = database.getConnection();

        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Lukuvinkki (name, type) VALUES (?,?)",
                Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, "Kirja testauksesta");
        stmt.setString(2, "book");

        if (stmt.executeUpdate() == 0) {
            throw new SQLException("Creating lukuvinkki failed, no rows affected.");
        }


        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                testiId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating lukuvinkki failed, no ID obtained");
            }
        }

        stmt.clearParameters();
        stmt = conn.prepareStatement("INSERT INTO Book (title, author, lukuvinkki_id) VALUES (?,?,?)");
        stmt.setString(1, "Kirja testauksesta");
        stmt.setString(2, "Kirjailija");
        stmt.setInt(3, testiId);
        stmt.execute();

        stmt.close();
        conn.close();
    }

    public void removeTestLukuvinkki() throws Exception {
        try {
            Connection conn = database.getConnection();

            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Book WHERE lukuvinkki_id = ?");
            stmt.setInt(1, testiId);
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("DELETE FROM Lukuvinkki WHERE id = ?");
            stmt.setInt(1, testiId);
            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void findAllReturnsCorrectBooks() throws Exception {
        setTestLukuvinkki();

        List<Lukuvinkki> books = bookDao.findAll();
        boolean found = false;
        for (Lukuvinkki book : books) {
            if (book.getTitle().equals("Kirja testauksesta")) {
                found = true;
                break;
            }
        }
        assertTrue(found);
        removeTestLukuvinkki();
    }

    @Test
    public void deleteRemovesRightLukuvinkki() throws Exception {
        setTestLukuvinkki();
        bookDao.delete(String.valueOf(testiId));
        List<Book> books = new ArrayList<>();
        try {
            Connection conn = database.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Book ORDER BY book_id");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                book.id = rs.getInt("book_id");
                book.title = rs.getString("title");
                book.author = rs.getString("author");

                books.add(book);
            }
            stmt.close();
            rs.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        boolean found = false;
        for (Book book : books) {
            if (book.getBooktitle().equals("Kirja testauksesta")) {
                found = true;
                break;
            }
        }
        assertFalse(found);

    }

    @Test
    public void checkDatabaseConnectionCallsDatabase() throws Exception {
        LukuvinkkiDao testDao = new BookDao(mockDB);
        bookDao.checkDatabaseConnection();
        verify(mockDB, times(1)).getConnection();
    }


    @Test
    public void insertAddsLukuvinkkiToDatabase() throws Exception {
        bookDao.insert("Kirjoista", "Kirjailija");
        Book book = new Book();
        try {
            Connection conn = database.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Book WHERE author = ?");
            stmt.setString(1, "Kirjailija");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                book.id = rs.getInt("book_id");
                book.title = rs.getString("title");
                book.author = rs.getString("author");
            }
            stmt.close();
            rs.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        int id = book.getId();

        assertTrue(book != null);
        Connection conn = database.getConnection();

        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Book WHERE book_id = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();

    }
}

