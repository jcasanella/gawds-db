package com.gawds.db.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import io.vavr.control.Option;

public class ConfigApp implements ServerConfig {
    Config config;

    public ConfigApp() {
        this.config = ConfigFactory.defaultApplication();
    }

    @Override
    public Option<Integer> getPort() {
        return Option.of(config.getInt("server.port"));
    }
}
