package com.github.hwywl.exception;

/**
 * WriterException
 *
 * @author YI
 * @date 2019-11-13
 */
public class WriterException extends Exception {

    public WriterException() {
    }

    public WriterException(String message) {
        super(message);
    }

    public WriterException(String message, Throwable cause) {
        super(message, cause);
    }

    public WriterException(Throwable cause) {
        super(cause);
    }

}
