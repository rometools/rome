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
import com.rometools.rome.feed.synd.SyndPerson;

import java.util.List;

public interface AtomLinkModule extends Module {

    String URI = "http://www.w3.org/2005/Atom";

    List<Link> getLinks();
    @Deprecated
    Link getLink();

    void setLinks(List<Link> links);
    @Deprecated
    void setLink(Link link);

    List<SyndPerson> getAuthors();

    void setAuthors(List<SyndPerson> authors);

    List<SyndPerson> getContributors();

    void setContributors(List<SyndPerson> contributors);

}
