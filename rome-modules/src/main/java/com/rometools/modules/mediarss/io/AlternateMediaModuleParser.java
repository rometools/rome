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


package com.rometools.modules.mediarss.io;

import org.jdom2.Namespace;

public class AlternateMediaModuleParser extends MediaModuleParser {

    private static final Namespace NS = Namespace.getNamespace("http://search.yahoo.com/mrss");

    public AlternateMediaModuleParser() {
        super();
    }

    @Override
    public String getNamespaceUri() {
        return "http://search.yahoo.com/mrss";
    }

    @Override
    public Namespace getNS() {
        return AlternateMediaModuleParser.NS;
    }
}
