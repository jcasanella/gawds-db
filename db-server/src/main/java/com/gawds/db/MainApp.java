package com.gawds.db;

import com.gawds.db.driver.Driver;
import com.gawds.db.guice.ServerModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainApp {

    private static final Logger LOG = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new ServerModule());
        Driver driver = injector.getInstance(Driver.class);
        driver.serverStart()
                .onSuccess(t -> LOG.info("Finishing App"))
                .onFailure(ex -> LOG.error(ex.getMessage(), ex));
    }
}
