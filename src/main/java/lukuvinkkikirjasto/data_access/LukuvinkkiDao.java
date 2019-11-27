package lukuvinkkikirjasto.data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import lukuvinkkikirjasto.domain.Kirja;
import lukuvinkkikirjasto.domain.Lukuvinkki;

public class LukuvinkkiDao {

    public static int valiAikainen_id = 6;

    public LukuvinkkiDao() throws Exception {
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


    public List<Kirja> getBooks() throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:lukuvinkit.db");
        PreparedStatement st = connection.prepareStatement("SELECT * FROM Book");
        ResultSet rs = st.executeQuery();

        System.out.println("Kirjavinkit:");

        List<Kirja> books = new ArrayList<>();

        while (rs.next()) {
            String name = rs.getString("name");
            String writer = rs.getString("writer");

            System.out.println(name + " - " + writer);

            Kirja kirja = new Kirja(name, writer);
            books.add(kirja);
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

    public void deleteBook(Integer lukuvinkki_id) throws Exception {

        Connection connection = DriverManager.getConnection("jdbc:sqlite:lukuvinkit.db");
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM Book WHERE id = ?");
        stmt.setInt(1, lukuvinkki_id);
        stmt.execute();
        connection.close();

    }
}


