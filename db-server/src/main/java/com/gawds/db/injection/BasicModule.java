package com.gawds.db.injection;

import com.gawds.db.network.AsyncServerImpl;
import com.gawds.db.network.Server;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class BasicModule extends AbstractModule {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    protected void configure() {
        try {
            bind(Server.class)
                    .toConstructor(AsyncServerImpl.class.getConstructor(Integer.class));
        } catch (NoSuchMethodException e) {
            LOG.error("No exists the constructor with port argument", e);
        }
        bind(Integer.class)
                .annotatedWith(Names.named("port"))
                .toInstance(8082);
    }
}
