package com.gawds.db.network;

public interface Server {
    void start() throws InterruptedException;
    void close();
}
