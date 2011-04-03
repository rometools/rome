/*
 * MediaModuleImpl.java
 *
 * Created on April 19, 2006, 1:17 AM
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
 */
package org.rometools.feed.module.mediarss;

import com.sun.syndication.feed.CopyFrom;
import java.io.Serializable;

import com.sun.syndication.feed.module.ModuleImpl;
import org.rometools.feed.module.mediarss.types.Metadata;
import org.rometools.feed.module.mediarss.types.PlayerReference;


/**
 *
 * This class represents feed/channel level elements for MediaRSS
 * @author cooper
 */
public class MediaModuleImpl extends ModuleImpl implements MediaModule, Serializable {
	private static final long serialVersionUID = 1506805082848531979L;

	private Metadata metadata;
    private PlayerReference player;

    /** Creates a new instance of MediaModuleImpl */
    public MediaModuleImpl() {
        this(MediaModule.class, MediaModule.URI);
    }

    /**
     * constructor that passes values up to ModuleImpl.
     * @param clazz
     * @param uri
     */
    public MediaModuleImpl(Class clazz, String uri) {
        super(clazz, uri);
    }

    public Class getInterface() {
        return MediaModule.class;
    }

    /**
     * Metadata for a feed.
     * @param metadata Metadata for a feed.
     */
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    /**
     * Metadata for a feed.
     * @return Metadata for a feed.
     */
    public Metadata getMetadata() {
        return metadata;
    }

    /**
     * Player for a feed.
     * @param player Player for a feed.
     */
    public void setPlayer(PlayerReference player) {
        this.player = player;
    }

    /**
     * Player for a feed.
     * @return Player for a feed.
     */
    public PlayerReference getPlayer() {
        return player;
    }

    public String getUri() {
        return MediaModule.URI;
    }

    public Object clone() {
        MediaModuleImpl m = new MediaModuleImpl();
        m.setMetadata((Metadata) metadata.clone());
        m.setPlayer(player);

        return m;
    }

    public void copyFrom(CopyFrom obj) {
        MediaModule m = (MediaModule) obj;
        this.metadata = (Metadata) m.getMetadata().clone();
        this.player = m.getPlayer();
    }
}
