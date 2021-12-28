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
package com.rometools.rome.feed.synd;

import com.rometools.rome.feed.CopyFrom;

public interface SyndEnclosure extends Cloneable, CopyFrom {
    /**
     * Returns the enclosure URL.
     * <p>
     *
     * @return the enclosure URL, <b>null</b> if none.
     *
     */
    public String getUrl();

    /**
     * Sets the enclosure URL.
     * <p>
     *
     * @param url the enclosure URL to set, <b>null</b> if none.
     *
     */
    public void setUrl(String url);

    /**
     * Returns the enclosure length.
     * <p>
     *
     * @return the enclosure length, <b>0</b> if none.
     *
     */
    public long getLength();

    /**
     * Sets the enclosure length.
     * <p>
     *
     * @param length the enclosure length to set, <b>0</b> if none.
     *
     */
    public void setLength(long length);

    /**
     * Returns the enclosure type.
     * <p>
     *
     * @return the enclosure type, <b>null</b> if none.
     *
     */
    public String getType();

    /**
     * Sets the enclosure type.
     * <p>
     *
     * @param type the enclosure type to set, <b>null</b> if none.
     *
     */
    public void setType(String type);

}
