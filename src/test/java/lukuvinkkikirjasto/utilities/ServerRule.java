package lukuvinkkikirjasto.utilities;

import lukuvinkkikirjasto.domain.Kirja;
import org.junit.rules.ExternalResource;
import spark.Spark;

public class ServerRule extends ExternalResource {
    
    private final int port;

    public ServerRule(int port) {
        this.port = port;
    }

    @Override
    protected void before() throws Throwable {
        Application.main(null);
    }

    @Override
    protected void after() {
        Spark.stop();
    }
    
}
