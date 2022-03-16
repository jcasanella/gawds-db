package com.gawds.db.guice;

import com.gawds.db.config.ConfigApp;
import com.gawds.db.config.ServerConfig;
import com.gawds.db.guice.annotations.ConfigAnnotation;
import com.gawds.db.guice.annotations.ServerAnnotation;
import com.gawds.db.network.ServerImpl;
import com.gawds.db.network.Server;
import com.google.inject.AbstractModule;

public class ServerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ServerConfig.class)
                .annotatedWith(ConfigAnnotation.class)
                .to(ConfigApp.class);

        bind(Server.class)
                .annotatedWith(ServerAnnotation.class)
                .to(ServerImpl.class);
    }
}
