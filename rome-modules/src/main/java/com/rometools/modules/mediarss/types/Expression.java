/* This code is currently released under the Mozilla Public License.
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
 * expression determines if the object is a sample or the full version of the object, or even if it
 * is a continuous stream (sample | full | nonstop). Default value is 'full'. It is an optional
 * attribute.
 */
public class Expression implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Represents a complete media object.
     */
    public static final Expression FULL = new Expression("full");

    /**
     * Represents a sample media object.
     */
    public static final Expression SAMPLE = new Expression("sample");

    /**
     * represents a streaming media object.
     */
    public static final Expression NONSTOP = new Expression("nonstop");
    private final String value;

    private Expression(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
