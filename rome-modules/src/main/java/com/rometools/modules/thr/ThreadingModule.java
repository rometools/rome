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
package com.rometools.modules.thr;

import com.rometools.rome.feed.module.Module;

/**
 * Currently no support for thr:count, thr:updated, thr:total link attributes.
 */
public interface ThreadingModule extends Module {

    public static final String URI = "http://purl.org/syndication/thread/1.0";

    public String getRef();

    public void setRef(String ref);

    public String getType();

    public void setType(String type);

    public String getHref();

    public void setHref(String href);

    public String getSource();

    public void setSource(String source);
}
