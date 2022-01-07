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

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Date;

import org.hamcrest.core.Is;
import org.junit.Test;

public class DatesTest {

    @Test
    public void testCopy() {

        final Date date = new Date();
        final Date nullDate = null;

        assertThat(Dates.copy(date), Is.is(notNullValue()));
        assertThat(Dates.copy(date).getTime(), Is.is(date.getTime()));
        assertThat(Dates.copy(nullDate), Is.is(nullValue()));

    }

}
