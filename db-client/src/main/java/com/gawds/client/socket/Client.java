package com.gawds.client.socket;

public interface Client {
    void start(String host, int port) throws InterruptedException;
    void close();
}
