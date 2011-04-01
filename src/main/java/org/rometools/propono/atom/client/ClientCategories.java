/*
* Licensed to the Apache Software Foundation (ASF) under one or more
*  contributor license agreements.  The ASF licenses this file to You
* under the Apache License, Version 2.0 (the "License"); you may not
* use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.  For additional information regarding
* copyright in this work, please see the NOTICE file in the top level
* directory of this distribution.
*/ 
package org.rometools.propono.atom.client;

import com.sun.syndication.feed.atom.Category;
import org.rometools.propono.atom.common.*;
import org.rometools.propono.utils.ProponoException;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;


/** 
 * Models an Atom protocol Categories element, which may contain ROME Atom 
 * {@link com.sun.syndication.feed.atom.Category} elements. 
 */
public class ClientCategories extends Categories {    
    private ClientCollection clientCollection = null;
    
    /** Load select from XML element */
    public ClientCategories(Element e, ClientCollection clientCollection) throws ProponoException { 
        this.clientCollection = clientCollection;
        parseCategoriesElement(e);
        if (getHref() != null) fetchContents();
    }
    
    public void fetchContents() throws ProponoException {
        GetMethod method = new GetMethod(getHrefResolved());
        clientCollection.addAuthentication(method);
        try {
            clientCollection.getHttpClient().executeMethod(method);         
            if (method.getStatusCode() != 200) {
                throw new ProponoException("ERROR HTTP status code=" + method.getStatusCode());
            }
            SAXBuilder builder = new SAXBuilder();
            Document catsDoc = builder.build(
                    new InputStreamReader(method.getResponseBodyAsStream())); 
            parseCategoriesElement(catsDoc.getRootElement());

        } catch (IOException ioe) {
            throw new ProponoException(
                "ERROR: reading out-of-line categories", ioe);
        } catch (JDOMException jde) {
            throw new ProponoException(
                "ERROR: parsing out-of-line categories", jde);
        } finally {
            method.releaseConnection();
        }
    }
}

