package lukuvinkkikirjasto.data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import lukuvinkkikirjasto.domain.*;


public interface LukuvinkkiDao {

    List<Lukuvinkki> findAll();
    void delete(String lukuvinkkiId);
}
