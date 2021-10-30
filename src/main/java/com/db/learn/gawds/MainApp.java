package com.db.learn.gawds;

import com.db.learn.gawds.driver.Driver;
import com.db.learn.gawds.injection.BasicModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainApp {

    private static final Logger LOG = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            Injector injector = Guice.createInjector(new BasicModule());
            Driver driver = injector.getInstance(Driver.class);
            driver.serverStart();
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }
}
