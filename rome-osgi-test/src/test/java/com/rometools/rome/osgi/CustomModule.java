/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rometools.rome.osgi;

import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleParser;

import org.jdom2.Element;
import org.jdom2.Namespace;

import java.util.Locale;

public class CustomModule implements Module {

    public static final String URI = "http://example.org";

    private String title;

    public CustomModule(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String getUri() {
        return URI;
    }

    @Override
    public Class<? extends CopyFrom> getInterface() {
        return getClass();
    }

    @Override
    public void copyFrom(CopyFrom obj) {
        title = ((CustomModule) obj).title;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static class Parser implements ModuleParser {

        public String getNamespaceUri() {
            return URI;
        }

        public Module parse(Element element, Locale locale) {
            return new CustomModule(element.getChildText("title", Namespace.getNamespace(getNamespaceUri())));
        }
    }
}
