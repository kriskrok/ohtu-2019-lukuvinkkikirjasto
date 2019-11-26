package lukuvinkkikirjasto.data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class LukuvinkkiDao {

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


    public void getBooks() throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:lukuvinkit.db");
        PreparedStatement st = connection.prepareStatement("SELECT * FROM Book");
        ResultSet rs = st.executeQuery();

        System.out.println("Kirjavinkit:");

        while (rs.next()) {
            String name = rs.getString("name");
            String writer = rs.getString("writer");

            System.out.println(name + " - " + writer);
        }

        st.close();
        rs.close();
        connection.close();
    }
}


