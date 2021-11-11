package com.gawds.db.driver;

import com.gawds.db.socket.Server;
import com.google.inject.Inject;

public class Driver {
    @Inject
    private Server server;

    public Driver() {
        Runtime.getRuntime()
                .addShutdownHook(new Thread(() -> server.close()));
    }

    public void serverStart() throws InterruptedException {
        this.server.start();
    }
}
