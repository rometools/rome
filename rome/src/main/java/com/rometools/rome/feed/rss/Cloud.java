/*
 * Copyright 2004 Sun Microsystems, Inc.
 * Copyright 2011 The ROME Team
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
 *
 */
package com.rometools.rome.feed.rss;

import java.io.Serializable;

import com.rometools.rome.feed.impl.ObjectBean;

/**
 * Bean for clouds of RSS feeds.
 */
public class Cloud implements Cloneable, Serializable {
    private static final long serialVersionUID = 1L;
    private final ObjectBean objBean;
    private String domain;
    private int port;
    private String path;
    private String registerProcedure;
    private String protocol;

    public Cloud() {
        objBean = new ObjectBean(this.getClass(), this);
    }

    /**
     * Creates a deep 'bean' clone of the object.
     * <p>
     *
     * @return a clone of the object.
     * @throws CloneNotSupportedException thrown if an element of the object cannot be cloned.
     *
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return objBean.clone();
    }

    /**
     * Indicates whether some other object is "equal to" this one as defined by the Object equals()
     * method.
     * <p>
     *
     * @param other he reference object with which to compare.
     * @return <b>true</b> if 'this' object is equal to the 'other' object.
     *
     */
    @Override
    public boolean equals(final Object other) {
        return objBean.equals(other);
    }

    /**
     * Returns a hashcode value for the object.
     * <p>
     * It follows the contract defined by the Object hashCode() method.
     * <p>
     *
     * @return the hashcode of the bean object.
     *
     */
    @Override
    public int hashCode() {
        return objBean.hashCode();
    }

    /**
     * Returns the String representation for the object.
     * <p>
     *
     * @return String representation for the object.
     *
     */
    @Override
    public String toString() {
        return objBean.toString();
    }

    /**
     * Returns the cloud domain.
     * <p>
     *
     * @return the cloud domain, <b>null</b> if none.
     *
     */
    public String getDomain() {
        return domain;
    }

    /**
     * Sets the cloud domain.
     * <p>
     *
     * @param domain the cloud domain to set, <b>null</b> if none.
     *
     */
    public void setDomain(final String domain) {
        this.domain = domain;
    }

    /**
     * Returns the cloud port.
     * <p>
     *
     * @return the cloud port, <b>null</b> if none.
     *
     */
    public int getPort() {
        return port;
    }

    /**
     * Sets the cloud port.
     * <p>
     *
     * @param port the cloud port to set, <b>null</b> if none.
     *
     */
    public void setPort(final int port) {
        this.port = port;
    }

    /**
     * Returns the cloud path.
     * <p>
     *
     * @return the cloud path, <b>null</b> if none.
     *
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the cloud path.
     * <p>
     *
     * @param path the cloud path to set, <b>null</b> if none.
     *
     */
    public void setPath(final String path) {
        this.path = path;
    }

    /**
     * Returns the cloud register procedure.
     * <p>
     *
     * @return the cloud register procedure, <b>null</b> if none.
     *
     */
    public String getRegisterProcedure() {
        return registerProcedure;
    }

    /**
     * Sets the cloud register procedure.
     * <p>
     *
     * @param registerProcedure the cloud register procedure to set, <b>null</b> if none.
     *
     */
    public void setRegisterProcedure(final String registerProcedure) {
        this.registerProcedure = registerProcedure;
    }

    /**
     * Returns the cloud protocol.
     * <p>
     *
     * @return the cloud protocol, <b>null</b> if none.
     *
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * Sets the cloud protocol.
     * <p>
     *
     * @param protocol the cloud protocol to set, <b>null</b> if none.
     *
     */
    public void setProtocol(final String protocol) {
        this.protocol = protocol;
    }

}
