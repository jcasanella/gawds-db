package com.db.learn.gawds.driver;

import com.db.learn.gawds.network.Server;
import com.google.inject.Inject;

public class Driver {
    @Inject
    private Server server;

    public Driver() {
        Runtime.getRuntime()
                .addShutdownHook(new Thread(() -> server.close()));
    }

    public void serverStart(int port) throws InterruptedException {
        this.server.start(port);
    }
}
