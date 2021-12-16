package com.gawds.exception;

public class GawdsAddressException extends Exception {
    public GawdsAddressException(String errorMessage) {
        super(errorMessage);
    }

    public GawdsAddressException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
