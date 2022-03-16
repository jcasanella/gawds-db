package com.gawds.db.config;

import io.vavr.control.Option;

public interface ServerConfig {
    Option<Integer> getPort();
}
