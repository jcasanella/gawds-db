package com.gawds.db.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigFactory;

public class ConfigApp implements ServerConfig {
    private final Config config;

    public ConfigApp() {
        this.config = ConfigFactory.defaultApplication();
    }

    public ConfigApp(String propertiesFile) {
        this.config = ConfigFactory.load(propertiesFile);
    }

    @Override
    public int getPort() throws ConfigException {
        return config.getInt("server.port");
    }
}
