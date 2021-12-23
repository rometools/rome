/*
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
 *
 */
package com.rometools.modules.mediarss.types;

import java.io.Serializable;
import java.net.URL;

/**
 * Optional tag to include machine-readable license information about a media object.
 * 
 * @since MediaRSS 1.5.0
 * 
 */
public class License implements Serializable {
    private static final long serialVersionUID = 8468812050262548541L;
    private String type;
    private URL href;
    private String value;

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public URL getHref() {
        return href;
    }

    public void setHref(final URL href) {
        this.href = href;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    // CHECKSTYLE:OFF
    @Override
    public String toString() {
        return "License [type=" + type + ", href=" + href + ", value=" + value + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (href == null ? 0 : href.hashCode());
        result = prime * result + (type == null ? 0 : type.hashCode());
        result = prime * result + (value == null ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final License other = (License) obj;
        if (href == null) {
            if (other.href != null) {
                return false;
            }
        } else if (!href.equals(other.href)) {
            return false;
        }
        if (type == null) {
            if (other.type != null) {
                return false;
            }
        } else if (!type.equals(other.type)) {
            return false;
        }
        if (value == null) {
            if (other.value != null) {
                return false;
            }
        } else if (!value.equals(other.value)) {
            return false;
        }
        return true;
    }
    // CHECKSTYLE:ON

}
