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
import java.util.Arrays;

/**
 * Optional tag to include embed information about a media object.
 * 
 * @since MediaRSS 1.5.0
 */
public class Embed implements Serializable {
    private static final long serialVersionUID = -6950838495477768173L;
    private Param[] params = new Param[0];
    private URL url;
    private Integer width;
    private Integer height;

    public URL getUrl() {
        return url;
    }

    public void setUrl(final URL url) {
        this.url = url;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(final Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(final Integer height) {
        this.height = height;
    }

    public Param[] getParams() {
        return params;
    }

    /**
     * @param params the embed params
     */
    public void setParams(final Param[] params) {
        if (params == null) {
            this.params = new Param[0];
        } else {
            this.params = params;
        }
    }

    // CHECKSTYLE:OFF
    @Override
    public String toString() {
        return "Embed [params=" + Arrays.toString(params) + ", url=" + url + ", width=" + width + ", height=" + height + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (height == null ? 0 : height.hashCode());
        result = prime * result + Arrays.hashCode(params);
        result = prime * result + (url == null ? 0 : url.hashCode());
        result = prime * result + (width == null ? 0 : width.hashCode());
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
        final Embed other = (Embed) obj;
        if (height == null) {
            if (other.height != null) {
                return false;
            }
        } else if (!height.equals(other.height)) {
            return false;
        }
        if (!Arrays.equals(params, other.params)) {
            return false;
        }
        if (url == null) {
            if (other.url != null) {
                return false;
            }
        } else if (!url.equals(other.url)) {
            return false;
        }
        if (width == null) {
            if (other.width != null) {
                return false;
            }
        } else if (!width.equals(other.width)) {
            return false;
        }
        return true;
    }

    // CHECKSTYLE:ON

    /**
     * param for embed.
     */
    public static class Param implements Serializable {
        private static final long serialVersionUID = -1191307096400967579L;
        private String name;
        private String value;

        /**
         */
        public Param() {
        }

        /**
         * @param name name of the param
         * @param value value of the param
         */
        public Param(final String name, final String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(final String name) {
            this.name = name;
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
            return "Param [name=" + name + ", value=" + value + "]";
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (name == null ? 0 : name.hashCode());
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
            final Param other = (Param) obj;
            if (name == null) {
                if (other.name != null) {
                    return false;
                }
            } else if (!name.equals(other.name)) {
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
}
