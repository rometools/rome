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
package com.rometools.propono.atom.client;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;

import com.rometools.propono.utils.ProponoException;
import com.rometools.rome.io.impl.Base64;

/**
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public class BasicAuthStrategy implements AuthStrategy {
    private final String credentials;

    public BasicAuthStrategy(final String username, final String password) {
        new Base64();
        credentials = new String(Base64.encode((username + ":" + password).getBytes()));
    }

    public void init() throws ProponoException {
        // op-op
    }

    @Override
    public void addAuthentication(final HttpClient httpClient, final HttpMethodBase method) throws ProponoException {
        httpClient.getParams().setAuthenticationPreemptive(true);
        final String header = "Basic " + credentials;
        method.setRequestHeader("Authorization", header);
    }
}
