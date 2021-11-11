package com.gawds.db.driver;

import com.gawds.db.socket.Server;
import com.google.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Driver {
    private Server server;

    private static final Logger LOG = LogManager.getLogger();

    @Inject
    public Driver(final Server server) {
        this.server = server;
    }

    public void serverStart() {
        try {
            this.server.start();
        }  catch (Exception ex) {
            LOG.error("Server error connection", ex);
        } finally {
            this.server.close();
        }
    }
}
