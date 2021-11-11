package com.gawds.db.socket;

public interface Server {
    void start() throws InterruptedException;
    void close();
}
