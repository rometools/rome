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

import static org.hamcrest.core.Is.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class AlternativesTest {

    @Test
    public void testFirstNotNull() {

        final Integer nullInteger = null;
        final Integer notNullInteger = 1;

        assertThat(Alternatives.firstNotNull(notNullInteger, nullInteger), is(notNullInteger));
        assertThat(Alternatives.firstNotNull(nullInteger, notNullInteger), is(notNullInteger));
        assertThat(Alternatives.firstNotNull(nullInteger, nullInteger), is(nullValue()));

    }

}
