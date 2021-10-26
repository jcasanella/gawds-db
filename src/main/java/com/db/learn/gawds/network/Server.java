package com.db.learn.gawds.network;

public interface Server {
    void start(int port) throws InterruptedException;
    void close();
}
