/*
 * Copyright 2004 Sun Microsystems, Inc.
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
package com.rometools.rome.feed.module;

import com.rometools.rome.feed.CopyFrom;

/**
 * Subject of the Dublin Core ModuleImpl.
 * <p>
 *
 * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.
 * @author Norbert Kiesel <nkiesel@metricstream.com>
 *
 */
public interface CBResource extends Cloneable, CopyFrom {
    /**
     * Returns the CB resource title.
     * <p>
     *
     * @return the CB resource title, <b>null</b> if none.
     *
     */
    String getTitle();

    /**
     * Sets the CB resource title.
     * <p>
     *
     * @param title the CB resource title to set, <b>null</b> if none.
     *
     */
    void setTitle(String title);

    /**
     * Returns the CB resource link.
     * <p>
     *
     * @return the CB resource link, <b>null</b> if none.
     *
     */
    String getLink();

    /**
     * Sets the CB resource link.
     * <p>
     *
     * @param link the CB resource link to set, <b>null</b> if none.
     *
     */
    void setLink(String link);

    /**
     * Returns the CB resource description.
     * <p>
     *
     * @return the CB resource description, <b>null</b> if none.
     *
     */
    String getDescription();

    /**
     * Sets the CB resource description.
     * <p>
     *
     * @param description the CB resource description to set, <b>null</b> if none.
     *
     */
    void setDescription(String description);

}
