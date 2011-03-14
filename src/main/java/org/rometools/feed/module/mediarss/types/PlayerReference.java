/*
 * PlayerReference.java
 *
 * Created on April 18, 2006, 7:18 PM
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
package org.rometools.feed.module.mediarss.types;

import com.sun.syndication.feed.impl.EqualsBean;
import com.sun.syndication.feed.impl.ToStringBean;

import java.io.Serializable;
import java.net.URI;


/**
 *
 * <strong>&lt;media:player&gt;</strong></p>
 * <p>Allows the media object to be accessed through a web browser media player console.
 * This element is required only if a direct media <em>url</em> attribute is not specified in the &lt;media:content&gt; element. It has 1 required attribute, and 2 optional attributes.</p>
 * <pre>        &lt;media:player url="http://www.foo.com/player?id=1111" height="200" width="400" /&gt;</pre>
 * <p><em>url</em> is the url of the player console that plays the media. It is a required attribute.</p>
 *
 * <p><em>height</em> is the height of the browser window that the <em>url</em> should be opened in. It is an optional attribute.</p>
 * <p><em>width</em> is the width of the browser window that the <em>url</em> should be opened in. It is an optional attribute.</p>
 * @author cooper
 */
public class PlayerReference implements Reference, Serializable {
	private static final long serialVersionUID = -2618211012392630834L;
	
	private Integer height;
    private Integer width;
    private URI url;

    /**
     * Creates a new instance of PlayerReference
     * @param url url of the player
     * @param width width of the player
     * @param height height of the player
     */
    public PlayerReference(URI url, Integer width, Integer height) {
        super();

        if (url == null) {
            throw new NullPointerException("url cannot be null.");
        }

        this.url = url;
        this.height = height;
        this.width = width;
    }

    /**
     * Constructs a new PlayerReference
     * @param url URL of the player
     */
    public PlayerReference(URI url) {
        this(url, null, null);
    }

    /**
     * Height of the player
     * @return Height of the player
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * URL of the player
     * @return URL of the player
     */
    public URI getUrl() {
        return url;
    }

    /**
     * Width of the player
     * @return Width of the player
     */
    public Integer getWidth() {
        return width;
    }

    public boolean equals(Object obj) {
        EqualsBean eBean = new EqualsBean(this.getClass(), this);

        return eBean.beanEquals(obj);
    }

    public int hashCode() {
        EqualsBean equals = new EqualsBean(this.getClass(), this);

        return equals.beanHashCode();
    }

    public String toString() {
        ToStringBean tsBean = new ToStringBean(this.getClass(), this);

        return tsBean.toString();
    }
}
