/*
 * Text.java
 *
 * Created on April 18, 2006, 10:25 PM
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
package org.rometools.feed.module.mediarss.types;

import java.io.Serializable;

import com.sun.syndication.feed.impl.EqualsBean;
import com.sun.syndication.feed.impl.ToStringBean;


/**
 * <strong>&lt;media:text&gt;</strong></p>
 * <p>Allows the inclusion of a text transcript, closed captioning, or lyrics of the media content. Many of these elements are permitted to provide a time series of text. In such cases, it is encouraged, but not required, that the elements be grouped by language and appear in time sequence order based on the <em>start</em> time. Elements can have overlapping <em>start</em> and <em>end</em> times. It has 4 optional attributes.</p><pre>        &lt;media:text type="plain" lang="en" start="00:00:03.000"
 *        end="00:00:10.000"&gt; Oh, say, can you see&lt;/media:text&gt;
 *
 *        &lt;media:text type="plain" lang="en" start="00:00:10.000"
 *        end="00:00:17.000"&gt;By the dawn's early light&lt;/media:text&gt;
 * </pre>
 * <p><em>type</em> specifies the type of text embedded. Possible values are either 'plain' or 'html'. Default value is 'plain'. All html must be entity-encoded. It is an optional attribute.</p>
 *
 *
 *
 *
 *
 *
 * <p><em>lang</em> is the primary language encapsulated in the media object. Language codes possible are detailed in RFC 3066. This attribute is used similar to the xml:lang attribute detailed in the XML 1.0 Specification (Third Edition). It is an optional attribute.</p>
 *
 * <p><em>start</em> specifies the start time offset that the text starts being relevant to the media object. An example of this would be for closed captioning.
 * It uses the NTP time code format (see: the time attribute used in &lt;media:thumbnail&gt;).&nbsp;It is an optional attribute.</p>
 *
 * <p><em>end</em> specifies the end time that the text is relevant.
 * If this attribute is not provided, and a <em>start</em> time is used, it is expected that the end time is either the end of the clip or the start of the next &lt;media:text&gt; element. </p>
 * @author cooper
 */
public class Text implements Serializable {
	private static final long serialVersionUID = 9043514380583850045L;
	private String type;
    private String value;
    private Time end;
    private Time start;

    /**
     * Creates a text object.
     * @param value value of the text
     */
    public Text(String value) {
        this.value = value;
    }

    /**
     * Creates a new instance of Text
     * @param type type of text
     * @param value value of text
     */
    public Text(String type, String value) {
        this.type = type;
        this.value = value;
    }

    /**
     * Creates a text object with start and end times
     * @param type type of text
     * @param value value of text
     * @param start start time
     * @param end end time
     */
    public Text(String type, String value, Time start, Time end) {
        this(type, value);
        this.start = start;
        this.end = end;
    }

    /**
     * End time of the text
     * @return End time of the text
     */
    public Time getEnd() {
        return end;
    }

    /**
     * Start time of the text
     * @return Start time of the text
     */
    public Time getStart() {
        return start;
    }

    /**
     * type of the text
     * @return type of the text
     */
    public String getType() {
        return this.type;
    }

    /**
     * Value of the text
     * @return type of the text
     */
    public String getValue() {
        return this.value;
    }

    public boolean equals(Object obj) {
        EqualsBean eBean = new EqualsBean(this.getClass(), this);

        return eBean.beanEquals(obj);
    }

    public int hashCode() {
        EqualsBean equals = new EqualsBean(this.getClass(), this);

        return equals.beanHashCode();
    }

    public String toString() {
        ToStringBean tsBean = new ToStringBean(this.getClass(), this);

        return tsBean.toString();
    }
}
