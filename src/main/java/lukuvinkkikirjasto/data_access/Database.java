package lukuvinkkikirjasto.data_access;

import java.sql.*;
import java.io.File;
import org.sqlite.SQLiteConfig;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Database {
    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("Unable to load JDBC driver, beware!" +e);
        }
    }

    private String databaseAddress;
    private String dbUser = "";
    private String dbPassword = "";

    public Database(String databaseAddress) {
        this.databaseAddress = databaseAddress;
        initialize();
    }

    public Database() {
        this("jdbc:sqlite:lukuvinkit.db");
    }

    /*
    In sqlite foreign key constraints are disabled by default (for backwards compatibility),
    so must be enabled separately for each database connection.
    See: https://sqlite.org/foreignkeys.html
     */
    public Connection getConnection() throws Exception{
        Connection connection = null;
        try {
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);

            connection = DriverManager.getConnection(databaseAddress, config.toProperties());

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return connection;
    }

    private void initialize() {
        if (!new File("lukuvinkit.db").exists()) {
            initializeDatabase();
        }

        try {
            Connection conn = DriverManager.getConnection(databaseAddress);

            StringBuilder sb = new StringBuilder();
            sb.append(System.lineSeparator());
            sb.append("Welcome to lukuvinkkikirjasto beta!").append(System.lineSeparator());
            sb.append("Database connection established, rejoice!").append(System.lineSeparator());
            sb.append("\t").append("It is currently: ").append(System.lineSeparator());
            sb.append("\t").append(ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME));
            sb.append(System.lineSeparator());
            System.out.println(sb.toString());

            conn.close();
        } catch (SQLException e) {
            initializeDatabase();
        }
    }

    public void initializeDatabase() {
        System.out.println("Am I here");
        try {
            Connection conn = getConnection();

            conn.createStatement().execute("PRAGMA foreign_keys = ON");

            conn.prepareStatement("DROP TABLE IF EXISTS Lukuvinkki;").executeUpdate();
            conn.prepareStatement("CREATE TABLE Lukuvinkki (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name VARCHAR(255) NOT NULL," +
                    "type VARCHAR(64) CHECK(Type IN ('book', 'podcast', 'video')) NOT NULL);").executeUpdate();


            conn.prepareStatement("DROP TABLE IF EXISTS Book;").executeUpdate();
            conn.prepareStatement("CREATE TABLE Book (book_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "title VARCHAR(255) CHECK(LENGTH(title) >= 3), author VARCHAR(127), description VARCHAR(1023)," +
                    "comment VARCHAR(1023), url VARCHAR(255), status INTEGER CHECK(status IN (0,1)) DEFAULT '0'," +
                    "read DATE, isbn VARCHAR(20) CHECK(LENGTH(isbn) <= 20), lukuvinkki_id INTEGER," +
                    "FOREIGN KEY (lukuvinkki_id) REFERENCES Lukuvinkki(id)" +
                        "ON UPDATE CASCADE ON DELETE CASCADE);").executeUpdate();

            conn.prepareStatement("DROP TABLE IF EXISTS Podcast;").executeUpdate();
            conn.prepareStatement("CREATE TABLE Podcast (podcast_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "episode_title VARCHAR(255) CHECK(LENGTH(episode_title) >= 3), creator VARCHAR(127) ,series VARCHAR(255)," +
                    "description VARCHAR(1023), comment VARCHAR(1023), url VARCHAR(255)," +
                    "status INTEGER CHECK(status IN (0,1)) DEFAULT '0', read DATE, lukuvinkki_id INTEGER," +
                    "FOREIGN KEY (lukuvinkki_id) REFERENCES Lukuvinkki(id)" +
                        "ON UPDATE CASCADE ON DELETE CASCADE);").executeUpdate();


            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
