package com.gawds.db.driver;

import com.gawds.db.socket.Server;
import com.google.inject.Inject;
import io.vavr.control.Try;

public class Driver {
    private Server server;

    @Inject
    public Driver(final Server server) {
        this.server = server;
    }

    public Try<Object> serverStart() {
        return Try.of(() -> {
            this.server.start();
            return null;
        }).andFinally(() -> this.server.close());
    }
}
