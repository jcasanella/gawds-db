package com.gawds.db.driver;

import com.gawds.db.guice.annotations.ServerAnnotation;
import com.gawds.db.network.Server;
import com.google.inject.Inject;
import io.vavr.control.Try;

public class Driver {
    private Server server;

    @Inject
    public Driver(@ServerAnnotation final Server server) {
        this.server = server;
    }

    public Try<Object> serverStart() {
        return Try.of(() -> {
            this.server.start();
            return null;
        }).andFinally(() -> this.server.close());
    }
}
