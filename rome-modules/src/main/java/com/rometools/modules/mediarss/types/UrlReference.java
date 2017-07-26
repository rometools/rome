/*
 * UrlReference.java
 *
 * Created on April 18, 2006, 7:18 PM
 *
 *
 * This code is currently released under the Mozilla Public License.
 * http://www.mozilla.org/MPL/
 *
 * Alternately you may apply the terms of the Apache Software License
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rometools.modules.mediarss.types;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;

import com.rometools.rome.feed.impl.EqualsBean;

/**
 * Used to indicate a URL primary reference for a MediaContent object.
 */
public class UrlReference implements Reference, Serializable {
    private static final long serialVersionUID = 1L;

    private final URI url;

    /**
     * @param url URL to the media source
     */
    public UrlReference(final URI url) {
        if (url == null) {
            throw new NullPointerException("url cannot be null.");
        }

        this.url = url;
    }

    /**
     * @param url String value of a URL
     * @throws java.net.MalformedURLException thrown on bad URLs
     */
    public UrlReference(final String url) throws URISyntaxException {
        super();

        if (url == null) {
            throw new NullPointerException("url cannot be null.");
        }

        this.url = new URI(url);
    }

    /**
     * Returns the URL value
     *
     * @return Returns the URL value
     */
    public URI getUrl() {
        return url;
    }

    @Override
    public boolean equals(final Object obj) {
        final EqualsBean eBean = new EqualsBean(this.getClass(), this);

        return eBean.beanEquals(obj);
    }

    @Override
    public int hashCode() {
        final EqualsBean equals = new EqualsBean(this.getClass(), this);

        return equals.beanHashCode();
    }

    @Override
    public String toString() {
        return url.toString();
    }
}
