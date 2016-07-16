/*
 * Copyright 2007 Sun Microsystems, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rometools.propono.atom.server;

/**
 * Thrown when a problem with configuration with the
 * {@link com.rometools.rome.propono.atom.server.AtomHandlerFactory} exists. This error will
 * typically be thrown when the class of a parser factory specified in the system properties cannot
 * be found or instantiated.
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public class FactoryConfigurationError extends Error {

    private static final long serialVersionUID = 1L;

    /**
     * <code>Exception</code> that represents the error.
     */
    private final Exception exception;

    /**
     * Create a new <code>FactoryConfigurationError</code> with no detail mesage.
     */
    public FactoryConfigurationError() {
        super();
        exception = null;
    }

    /**
     * Create a new <code>FactoryConfigurationError</code> with the <code>String </code> specified
     * as an error message.
     *
     * @param msg The error message for the exception.
     */
    public FactoryConfigurationError(final String msg) {
        super(msg);
        exception = null;
    }

    /**
     * Create a new <code>FactoryConfigurationError</code> with a given <code>Exception</code> base
     * cause of the error.
     *
     * @param e The exception to be encapsulated in a FactoryConfigurationError.
     */
    public FactoryConfigurationError(final Exception e) {
        super(e.toString());
        exception = e;
    }

    /**
     * Create a new <code>FactoryConfigurationError</code> with the given <code>Exception</code>
     * base cause and detail message.
     *
     * @param e The exception to be encapsulated in a FactoryConfigurationError
     * @param msg The detail message.
     */
    public FactoryConfigurationError(final Exception e, final String msg) {
        super(msg);
        exception = e;
    }

    /**
     * Return the message (if any) for this error . If there is no message for the exception and
     * there is an encapsulated exception then the message of that exception, if it exists will be
     * returned. Else the name of the encapsulated exception will be returned.
     *
     * @return The error message.
     */
    @Override
    public String getMessage() {
        final String message = super.getMessage();

        if (message == null && exception != null) {
            return exception.getMessage();
        }

        return message;
    }

    /**
     * Return the actual exception (if any) that caused this exception to be raised.
     *
     * @return The encapsulated exception, or null if there is none.
     */
    public Exception getException() {
        return exception;
    }
}
