package com.gawds.client;

import com.gawds.client.driver.Driver;
import com.gawds.client.guice.BasicModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainApp {
    private static final Logger LOG = LogManager.getLogger();

    public static void main(String[] args) {
        String host = args[0];
        int port = Integer.parseInt(args[1]);

        LOG.info("Starting client");
        Injector injector = Guice.createInjector(new BasicModule());
        Driver driver = injector.getInstance(Driver.class);
        driver.start(host, port);
    }
}
