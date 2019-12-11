package lukuvinkkikirjasto.data_access;

import java.util.*;

import lukuvinkkikirjasto.domain.*;


public interface LukuvinkkiDao {

    List<Lukuvinkki> findAll();

    void delete(String lukuvinkkiId);
}
