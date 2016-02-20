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

import java.util.Date;

public final class Dates {

    private Dates() {
    }

    /**
     * Creates a copy on a Date.
     *
     * @param d The Date to copy, can be null
     * @return null when the input Date was null, a copy of the Date otherwise
     */
    public static Date copy(final Date d) {
        if (d == null) {
            return null;
        } else {
            return new Date(d.getTime());
        }
    }

}
