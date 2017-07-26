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
package com.rometools.propono.utils;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Base Propono exception class.
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public class ProponoException extends Exception {

    private static final long serialVersionUID = 1L;
    private Throwable mRootCause = null;

    /**
     * Construct emtpy exception object.
     */
    public ProponoException() {
        super();
    }

    /**
     * Construct ProponoException with message string.
     *
     * @param s Error message string.
     */
    public ProponoException(final String s) {
        super(s);
    }

    /**
     * Construct ProponoException with message string.
     *
     * @param s Error message string.
     */
    public ProponoException(final String s, final String longMessage) {
        super(s);
    }

    /**
     * Construct ProponoException, wrapping existing throwable.
     *
     * @param s Error message
     * @param t Existing connection to wrap.
     */
    public ProponoException(final String s, final Throwable t) {
        super(s);
        mRootCause = t;
    }

    /**
     * Construct ProponoException, wrapping existing throwable.
     *
     * @param s Error message
     * @param t Existing connection to wrap.
     */
    public ProponoException(final String s, final String longMessage, final Throwable t) {
        super(s);
        mRootCause = t;
    }

    /**
     * Construct ProponoException, wrapping existing throwable.
     *
     * @param t Existing exception to be wrapped.
     */
    public ProponoException(final Throwable t) {
        mRootCause = t;
    }

    /**
     * Get root cause object, or null if none.
     *
     * @return Root cause or null if none.
     */
    public Throwable getRootCause() {
        return mRootCause;
    }

    /**
     * Get root cause message.
     *
     * @return Root cause message.
     */
    public String getRootCauseMessage() {
        String rcmessage = null;
        if (getRootCause() != null) {
            if (getRootCause().getCause() != null) {
                rcmessage = getRootCause().getCause().getMessage();
            }
            rcmessage = rcmessage == null ? getRootCause().getMessage() : rcmessage;
            rcmessage = rcmessage == null ? super.getMessage() : rcmessage;
            rcmessage = rcmessage == null ? "NONE" : rcmessage;
        }
        return rcmessage;
    }

    /**
     * Print stack trace for exception and for root cause exception if htere is one.
     *
     * @see java.lang.Throwable#printStackTrace()
     */
    @Override
    public void printStackTrace() {
        super.printStackTrace();
        if (mRootCause != null) {
            System.out.println("--- ROOT CAUSE ---");
            mRootCause.printStackTrace();
        }
    }

    /**
     * Print stack trace for exception and for root cause exception if htere is one.
     *
     * @param s Stream to print to.
     */
    @Override
    public void printStackTrace(final PrintStream s) {
        super.printStackTrace(s);
        if (mRootCause != null) {
            s.println("--- ROOT CAUSE ---");
            mRootCause.printStackTrace(s);
        }
    }

    /**
     * Print stack trace for exception and for root cause exception if htere is one.
     *
     * @param s Writer to write to.
     */
    @Override
    public void printStackTrace(final PrintWriter s) {
        super.printStackTrace(s);
        if (null != mRootCause) {
            s.println("--- ROOT CAUSE ---");
            mRootCause.printStackTrace(s);
        }
    }

}
