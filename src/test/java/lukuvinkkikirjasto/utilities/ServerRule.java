package lukuvinkkikirjasto.utilities;

import lukuvinkkikirjasto.domain.*;
import lukuvinkkikirjasto.utilities.*;
import lukuvinkkikirjasto.data_access.*;
import org.junit.rules.ExternalResource;
import spark.Spark;

public class ServerRule extends ExternalResource {
    
    private final int port;

    public ServerRule(int port) {
        this.port = port;
    }

    @Override
    protected void before() throws Throwable {
        Spark.port(port);
        LukuvinkkiDao dao = new BookDaoForTests();
        Application.setDao(dao);
        Application.main(null);
    }

    @Override
    protected void after() {
        Spark.stop();
    }
    
}
