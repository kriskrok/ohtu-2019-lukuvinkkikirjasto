package lukuvinkkikirjasto.data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import lukuvinkkikirjasto.domain.*;

public class BookDao implements LukuvinkkiDao {


    public BookDao() throws Exception {
        checkDatabaseConnection();
    }

    private void checkDatabaseConnection() throws Exception {

        Connection c = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:lukuvinkit.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            //System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    public List<Book> getBooks() throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:lukuvinkit.db");
        PreparedStatement st = connection.prepareStatement("SELECT * FROM Book");
        ResultSet rs = st.executeQuery();

        System.out.println("Kirjavinkit:");

        List<Book> books = new ArrayList<>();

        while (rs.next()) {
            String name = rs.getString("name");
            String writer = rs.getString("writer");

            System.out.println(name + " - " + writer);

            Book book = new Book(name, writer);
            books.add(book);
        }

        st.close();
        rs.close();
        connection.close();
        return books;
    }

    public void newBook(String name, String writer) throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:lukuvinkit.db");

        PreparedStatement stmt2 = connection.prepareStatement("INSERT INTO Lukuvinkki (type) VALUES ('book')");
        stmt2.execute();

        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Book (lukuvinkki_id, name, writer) VALUES ( (SELECT MAX(id) FROM Lukuvinkki) , ?, ?)");
        stmt.setString(1, name);
        stmt.setString(2, writer);
        stmt.execute();

        connection.close();
    }

    public void deleteBook(String id) throws Exception {
        try {
            Integer.parseInt(id);
        } catch (Throwable t) {
            return;
        }

        Connection connection = DriverManager.getConnection("jdbc:sqlite:lukuvinkit.db");
        PreparedStatement stmt2 = connection.prepareStatement("DELETE FROM Book WHERE lukuvinkki_id = ?");
        stmt2.setInt(1, Integer.parseInt(id));
        stmt2.execute();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM Lukuvinkki WHERE id = ?");
        stmt.setInt(1, Integer.parseInt(id));
        stmt.execute();

        connection.close();
    }
}
