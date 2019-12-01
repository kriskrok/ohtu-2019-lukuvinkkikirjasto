package lukuvinkkikirjasto.data_access;

import lukuvinkkikirjasto.domain.*;
import lukuvinkkikirjasto.utilities.*;
import lukuvinkkikirjasto.data_access.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.sql.*;
import java.util.*;

public class BookDaoTest {

    private BookDao dao;
    private Database database = new Database();
    int testiId;
    private Database mockDB;


    @Before
    public void createNewDao() throws Exception {
        this.dao = new BookDao(database);
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

    public void removeTestLukuvinkki() throws Exception{
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
    public void findAllReturnsCorrectBooks() throws Exception{
        setTestLukuvinkki();
        List<Book> books = dao.findAll();
        boolean found=false;
        for (Book book:books){
            if (book.getBooktitle().equals("Kirja testauksesta")){
                found= true;
                break;
            }
        }
        assertTrue(found);
        removeTestLukuvinkki();
    }

    @Test
    public void deleteRemovesRightLukuvinkki() throws Exception{
        setTestLukuvinkki();
        dao.delete(String.valueOf(testiId));
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
        boolean found=false;
        for (Book book:books){
            if (book.getBooktitle().equals("Kirja testauksesta")){
                found= true;
                break;
            }
        }
        assertFalse(found);

    }

    @Test
    public void checkDatabaseConnectionCallsDatabase() throws  Exception{
        mockDB = mock(Database.class);
        LukuvinkkiDao testDao  = new BookDao(mockDB);
        dao.checkDatabaseConnection();
        verify(mockDB, times(1)).getConnection();
    }
}

/*  @Test
    public void aNewBookWithValidInputsCanBeAdded() throws Exception {
        dao.newBook("TestName", "TestWriter", 2);
        this.books = dao.getBooks();
        assertEquals(1, this.books.size());
    }
*/
