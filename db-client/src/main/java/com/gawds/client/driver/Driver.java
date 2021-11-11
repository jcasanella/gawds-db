package com.gawds.client.driver;

import com.gawds.client.socket.Client;
import com.google.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Driver {
    private Client client;

    private static final Logger LOG = LogManager.getLogger();

    @Inject
    public Driver(final Client client) {
        this.client = client;
    }

    public void start(String host, int port) {
        try {
            client.start(host, port);
        } catch(Exception ex) {
            LOG.error("Client error during the connection", ex);
        } finally {
            client.close();
        }
    }
}
