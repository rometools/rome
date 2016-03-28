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
package com.rometools.modules.atom.modules;

import com.rometools.rome.feed.atom.Link;
import com.rometools.rome.feed.module.Module;

public interface AtomLinkModule extends Module {

    public static final String URI = "http://www.w3.org/2005/Atom";

    public Link getLink();

    public void setLink(Link link);
}
