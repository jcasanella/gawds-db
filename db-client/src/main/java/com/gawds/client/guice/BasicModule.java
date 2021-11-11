package com.gawds.client.guice;

import com.gawds.client.socket.Client;
import com.gawds.client.socket.ClientImpl;
import com.google.inject.AbstractModule;

public class BasicModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Client.class).to(ClientImpl.class);
    }
}
