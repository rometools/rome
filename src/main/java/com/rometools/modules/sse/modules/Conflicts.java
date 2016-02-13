/*
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
package com.rometools.modules.sse.modules;

import com.rometools.rome.feed.CopyFrom;

/**
 * <sx:conflicts> element within <sx:sync>
 * <p>
 * The sx:conflicts element MUST contain one or more sx:conflict sub-elements.
 */
public class Conflicts extends SSEModule {
    private static final long serialVersionUID = 1L;
    public static final String NAME = "conflicts";

    @Override
    public void copyFrom(final CopyFrom obj) {
        // nothing to copy, just a place-holder
    }
}
