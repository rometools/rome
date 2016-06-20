/*
 * AbstractSchemeValue.java
 *
 * Created on April 18, 2006, 8:03 PM
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

/**
 * Simple data bean parent for scheme-value type entities.
 */
public abstract class AbstractSchemeValue implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String scheme;
    private final String value;

    public AbstractSchemeValue(final String scheme, final String value) {
        if (scheme == null || value == null) {
            throw new NullPointerException("Scheme and value cannot be null.");
        }

        this.scheme = scheme;
        this.value = value;
    }

    public String getScheme() {
        return scheme;
    }

    public String getValue() {
        return value;
    }
}
