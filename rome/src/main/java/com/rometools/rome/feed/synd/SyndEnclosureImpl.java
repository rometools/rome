/*
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
package com.rometools.rome.feed.synd;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.impl.CopyFromHelper;
import com.rometools.rome.feed.impl.ObjectBean;

public class SyndEnclosureImpl implements Serializable, SyndEnclosure {

    private static final long serialVersionUID = 1L;

    private static final CopyFromHelper COPY_FROM_HELPER;

    private final ObjectBean objBean;

    private String url;
    private String type;
    private long length;

    static {
        final Map<String, Class<?>> basePropInterfaceMap = new HashMap<String, Class<?>>();
        basePropInterfaceMap.put("url", String.class);
        basePropInterfaceMap.put("type", String.class);
        basePropInterfaceMap.put("length", Long.TYPE);

        final Map<Class<? extends CopyFrom>, Class<?>> basePropClassImplMap = Collections.<Class<? extends CopyFrom>, Class<?>> emptyMap();

        COPY_FROM_HELPER = new CopyFromHelper(SyndEnclosure.class, basePropInterfaceMap, basePropClassImplMap);
    }

    public SyndEnclosureImpl() {
        objBean = new ObjectBean(SyndEnclosure.class, this);
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
     * Returns the enclosure URL.
     * <p/>
     *
     * @return the enclosure URL, <b>null</b> if none.
     */
    @Override
    public String getUrl() {
        return url;
    }

    /**
     * Sets the enclosure URL.
     * <p/>
     *
     * @param url the enclosure URL to set, <b>null</b> if none.
     */
    @Override
    public void setUrl(final String url) {
        this.url = url;
    }

    /**
     * Returns the enclosure length.
     * <p/>
     *
     * @return the enclosure length, <b>null</b> if none.
     */
    @Override
    public long getLength() {
        return length;
    }

    /**
     * Sets the enclosure length.
     * <p/>
     *
     * @param length the enclosure length to set, <b>null</b> if none.
     */
    @Override
    public void setLength(final long length) {
        this.length = length;
    }

    /**
     * Returns the enclosure type.
     * <p/>
     *
     * @return the enclosure type, <b>null</b> if none.
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * Sets the enclosure type.
     * <p/>
     *
     * @param type the enclosure type to set, <b>null</b> if none.
     */
    @Override
    public void setType(final String type) {
        this.type = type;
    }

    @Override
    public Class<SyndEnclosure> getInterface() {
        return SyndEnclosure.class;
    }

    @Override
    public void copyFrom(final CopyFrom obj) {
        COPY_FROM_HELPER.copy(this, obj);
    }

}
