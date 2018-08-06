package com.example.addressbook.exception;

/**
 * Created by magg on 8/4/18.
 */
public class LoadException extends RuntimeException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     *
     *
     * @param error
     *            the error
     * @param t
     *            the t
     */
    public LoadException(String error, Exception t) {
        super(error, t);
    }

}
