package com.rometools.propono.atom.server;

class ConfigurationError extends Error {

    private static final long serialVersionUID = 1L;

    private final Exception exception;

    /**
     * Construct a new instance with the specified detail string and exception.
     */
    ConfigurationError(final String msg, final Exception x) {
        super(msg);
        exception = x;
    }

    Exception getException() {
        return exception;
    }

}