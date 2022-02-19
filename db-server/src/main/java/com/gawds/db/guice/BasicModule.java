package com.gawds.db.guice;

import com.gawds.db.socket.ServerImpl;
import com.gawds.db.socket.Server;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BasicModule extends AbstractModule {

    private static final Logger LOG = LoggerFactory.getLogger(BasicModule.class);

    @Override
    protected void configure() {
        try {
            bind(Server.class)
                    .toConstructor(ServerImpl.class.getConstructor(Integer.class));
        } catch (NoSuchMethodException e) {
            LOG.error("No exists the constructor with port argument", e);
        }
        bind(Integer.class)
                .annotatedWith(Names.named("port"))
                .toInstance(8082);
    }
}
