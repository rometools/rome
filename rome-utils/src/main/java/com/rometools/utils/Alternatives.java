/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rometools.utils;

public final class Alternatives {

    private Alternatives() {
    }

    /**
     * Returns the first object that is not null
     *
     * @param objects The objects to process
     * @return The first value that is not null. null when there is no not-null value
     */
    public static <T> T firstNotNull(final T... objects) {
        for (final T object : objects) {
            if (object != null) {
                return object;
            }
        }
        return null;
    }

}
