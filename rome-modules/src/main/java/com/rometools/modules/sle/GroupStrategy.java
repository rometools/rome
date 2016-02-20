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
package com.rometools.modules.sle;

import com.rometools.modules.sle.io.ModuleParser;
import com.rometools.modules.sle.types.EntryValue;
import com.rometools.modules.sle.types.Group;
import com.rometools.rome.feed.module.Extendable;

class GroupStrategy implements ValueStrategy {

    @Override
    public EntryValue getValue(final Extendable extendable, final Object value) {
        try {
            final String uri = ModuleParser.TEMP.getURI();
            final SleEntry entry = (SleEntry) extendable.getModule(uri);
            final Group group = (Group) value;
            return entry.getGroupByElement(group);
        } catch (final NullPointerException npe) {
            return null;
        }
    }

}