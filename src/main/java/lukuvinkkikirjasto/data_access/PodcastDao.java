package lukuvinkkikirjasto.data_access;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import lukuvinkkikirjasto.domain.*;

public class PodcastDao implements LukuvinkkiDao {
    private final String DELETE = "DELETE FROM Podcast WHERE podcast_id = ?";
    private final String FIND_BY_ID = "SELECT * FROM Podcast WHERE lukuvinkki_id = ?";
    private final String FIND_ALL = "SELECT * FROM Podcast ORDER BY podcast_id";
    private final String INSERT = "INSERT INTO Podcast (episode_title, creator, series, description, url, lukuvinkki_id) VALUES (?,?,?,?,?,?)";
    private final String UPDATE = "UPDATE Podcast SET episode_title = ?, creator = ?, series = ?, description = ?, url = ?,  WHERE podcast_id = ?";

    Database database;

    public PodcastDao(Database db) throws Exception {
        this.database = db;
        checkDatabaseConnection();
    }

    public void checkDatabaseConnection() throws Exception {
        Connection conn = null;

        try {
            conn = database.getConnection();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public List<Lukuvinkki> findAll() {
        List<Lukuvinkki> podcasts = new ArrayList<>();
        try {
            Connection conn = database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(FIND_ALL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Podcast podcast = new Podcast();
                podcast.id = rs.getInt("podcast_id");
                podcast.creator = rs.getString("creator");
                podcast.title = rs.getString("episode_title");
                podcast.description = rs.getString("description");
                podcast.series = rs.getString("series");
                podcast.url = rs.getString("url");
                podcasts.add(podcast);
            }

            stmt.close();
            rs.close();
            conn.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        Collections.sort(podcasts);
        return podcasts;
    }

    public void insert(String title, String series, String creator, String url, String description) throws Exception {
        Connection conn = database.getConnection();

        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Lukuvinkki (name, type) VALUES (?,?)",
                Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, title);
        stmt.setString(2, "podcast");

        if (stmt.executeUpdate() == 0) {
            throw new SQLException("Creating lukuvinkki failed, no rows affected.");
        }

        int lukuvinkkiId;

        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                lukuvinkkiId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating lukuvinkki failed, no ID obtained");
            }
        }

        stmt.clearParameters();
        stmt = conn.prepareStatement(INSERT);
        stmt.setString(1, title);
        stmt.setString(2, creator);
        stmt.setString(3, series);
        stmt.setString(4, description);
        stmt.setString(5, url);
        stmt.setInt(6, lukuvinkkiId);
        stmt.execute();

        stmt.close();
        conn.close();
    }

    public void delete(String lukuvinkkiId) {
        try {
            Connection conn = database.getConnection();

            PreparedStatement stmt = conn.prepareStatement(DELETE);
            stmt.setInt(1, Integer.parseInt(lukuvinkkiId));
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("DELETE FROM Lukuvinkki WHERE id = ?");
            stmt.setInt(1, Integer.parseInt(lukuvinkkiId));
            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
