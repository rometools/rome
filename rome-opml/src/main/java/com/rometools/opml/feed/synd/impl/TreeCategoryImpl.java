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
package com.rometools.opml.feed.synd.impl;

import com.rometools.rome.feed.synd.SyndCategory;
import com.rometools.rome.feed.synd.SyndCategoryImpl;

/**
 * @author cooper
 */
public class TreeCategoryImpl extends SyndCategoryImpl {

    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(final Object o) {
        final SyndCategory c = (SyndCategory) o;
        if (c.getTaxonomyUri() != null && c.getTaxonomyUri().equals(getTaxonomyUri())) {
            return true;
        } else {
            return false;
        }
    }

}
