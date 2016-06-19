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
package com.rometools.rome.io;

import org.jdom2.JDOMException;
import org.jdom2.input.sax.XMLReaderJDOMFactory;
import org.jdom2.input.sax.XMLReaders;
import org.xml.sax.XMLReader;

/*
 *  This code is needed to fix the security problem outlined in http://www.securityfocus.com/archive/1/297714
 *
 * Unfortunately there isn't an easy way to check if an XML parser supports a particular feature, so
 * we need to set it and catch the exception if it fails. We also need to subclass the JDom SAXBuilder
 * class in order to get access to the underlying SAX parser - otherwise the features don't get set until
 * we are already building the document, by which time it's too late to fix the problem.
 *
 * Crimson is one parser which is known not to support these features.
 *
 */
public class SAXBuilder extends org.jdom2.input.SAXBuilder {

    public SAXBuilder(final XMLReaderJDOMFactory factory) {
        super(factory);
    }

    /**
     *
     * @deprecated use SAXBuilder(XMLReaderJDOMFactory) with either XMLReaders.DTDVALIDATING or
     *             XMLReaders.NONVALIDATING
     */
    @Deprecated
    public SAXBuilder(final boolean validate) {
        super(validate ? XMLReaders.DTDVALIDATING : XMLReaders.NONVALIDATING);
    }

    @Override
    public XMLReader createParser() throws JDOMException {
        return super.createParser();
    }

}
