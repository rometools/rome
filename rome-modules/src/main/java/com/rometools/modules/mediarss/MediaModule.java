/*
 * Copyright 2006 Nathanial X. Freitas, openvision.tv
 *
 * This code is currently released under the Mozilla Public License.
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
 *
 */
package com.rometools.modules.mediarss;

import com.rometools.modules.mediarss.types.Metadata;
import com.rometools.modules.mediarss.types.PlayerReference;
import com.rometools.rome.feed.module.Module;

/**
 * This is the base module for MediaRSS.
 * <p>
 * It represents information that can be stored at the feed level, as well is a base for entry level
 * information, as the same information can apply.
 * </p>
 */
public interface MediaModule extends Module {
    // the URI of the MediaRSS specification as hosted by yahoo
    public final static String URI = "http://search.yahoo.com/mrss/";

    /**
     * Returns Metadata associated with the feed.
     *
     * @return Returns Metadata associated with the feed.
     */
    public Metadata getMetadata();

    /**
     * Returns a player reference associated with the feed.
     *
     * @return Returns a player reference associated with the feed.
     */
    public PlayerReference getPlayer();
}
