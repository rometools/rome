/*
 * Hash.java
 *
 * Created on April 18, 2006, 8:06 PM
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

import java.io.Serializable;

import com.sun.syndication.feed.impl.EqualsBean;
import com.sun.syndication.feed.impl.ToStringBean;


/**
 *
 * <strong>&lt;media:hash&gt;</strong></p>
 *
 * <p>This is the hash of the binary media file. It can appear multiple times as long as each instance is a different <em>algo</em>. It has 1 optional attribute.</p><p></p>
 *
 * <pre>        &lt;media:hash algo="md5"&gt;dfdec888b72151965a34b4b59031290a&lt;/media:hash&gt;</pre>
 *
 * <p><em>algo</em> indicates the algorithm used to create the hash. Possible values are 'md5' and 'sha-1'. Default value is 'md5'. It is an optional attribute.
 * @author cooper
 */
public class Hash extends AbstractSchemeValue implements Serializable {
	private static final long serialVersionUID = 3566980635881544337L;

	/**
     * Creates a new instance of Hash
     * @param algorithm algoright used
     * @param value value of the hash
     */
    public Hash(String algorithm, String value) {
        super(algorithm == null ? "MD5": algorithm , value);
    }
    /**
     * Creates a new instance of Hash assuming the default algorithm of MD5
     * @param algorithm algoright used
     */
    public Hash(String value ){
        this( null, value);
    }

    /**
     * Alsorithm used for the hash
     * @return Alsorithm used for the hash
     */
    public String getAlgorithm() {
        return super.getScheme();
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
