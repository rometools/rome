/*
 * Copyright 2009 Dave Johnson (Blogapps project)
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
 */
package org.rometools.propono.atom.client;

import com.sun.syndication.io.impl.Base64;
import org.rometools.propono.utils.ProponoException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;


public class BasicAuthStrategy implements AuthStrategy {
    private String credentials;

    public BasicAuthStrategy(String username, String password) {
        this.credentials =
            new String(new Base64().encode((username + ":" + password).getBytes()));
    }

    public void init() throws ProponoException {
        // op-op
    }

    public void addAuthentication(HttpClient httpClient, HttpMethodBase method) throws ProponoException {
        httpClient.getParams().setAuthenticationPreemptive(true);
        String header = "Basic " + credentials;
        method.setRequestHeader("Authorization", header);
    }
}
