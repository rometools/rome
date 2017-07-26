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
package com.rometools.rome.feed.synd.impl;

/**
 * Utility class for normalizing an URI as specified in RFC 2396bis.
 */
public class URINormalizer {

    private URINormalizer() {
    }

    /**
     * Normalizes an URI as specified in RFC 2396bis.
     * <p>
     *
     * @param uri to normalize.
     * @return the normalized value of the given URI, or <b>null</b> if the given URI was
     *         <b>null</b>.
     */
    public static String normalize(final String uri) {
        String normalizedUri = null;
        if (uri != null) {
            normalizedUri = uri; // TODO THIS HAS TO BE IMPLEMENTED
        }
        return normalizedUri;
    }
}
