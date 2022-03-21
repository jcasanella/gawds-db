package com.gawds.db.config;

import com.typesafe.config.ConfigException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigAppTest {

    @DisplayName("A port property defined in the config should return the port value")
    @Test
    void getPortTest() {
        ServerConfig configApp = new ConfigApp();
        int port = configApp.getPort();
        assertThat(port).isEqualTo(8082);
    }

    @DisplayName("A port property not defined or invalid should throw an exception")
    @ParameterizedTest
    @ValueSource(strings = {"applicationNoPort", "applicationInvalidPort"})
    void getNotDefinedPortTest(String input) {
        ServerConfig configApp = new ConfigApp(input);
        assertThrows(ConfigException.class, configApp::getPort);
    }
}