package com.db.learn.gawds.injection;

import com.db.learn.gawds.network.AsyncServerImpl;
import com.db.learn.gawds.network.Server;
import com.google.inject.AbstractModule;


public class BasicModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Server.class).to(AsyncServerImpl.class);
    }
}
