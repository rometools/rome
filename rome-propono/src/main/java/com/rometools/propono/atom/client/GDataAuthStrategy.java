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
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.rometools.propono.utils.ProponoException;

/**
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public class GDataAuthStrategy implements AuthStrategy {
    private final String email;
    private final String password;
    private final String service;
    private String authToken;

    public GDataAuthStrategy(final String email, final String password, final String service) throws ProponoException {
        this.email = email;
        this.password = password;
        this.service = service;
        init();
    }

    private void init() throws ProponoException {
        try {
            final HttpClient httpClient = new HttpClient();
            final PostMethod method = new PostMethod("https://www.google.com/accounts/ClientLogin");
            final NameValuePair[] data = { new NameValuePair("Email", email), new NameValuePair("Passwd", password),
                    new NameValuePair("accountType", "GOOGLE"), new NameValuePair("service", service),
                    new NameValuePair("source", "ROME Propono Atompub Client") };
            method.setRequestBody(data);
            httpClient.executeMethod(method);

            final String responseBody = method.getResponseBodyAsString();
            final int authIndex = responseBody.indexOf("Auth=");

            authToken = "GoogleLogin auth=" + responseBody.trim().substring(authIndex + 5);

        } catch (final Throwable t) {
            t.printStackTrace();
            throw new ProponoException("ERROR obtaining Google authentication string", t);
        }
    }

    @Override
    public void addAuthentication(final HttpClient httpClient, final HttpMethodBase method) throws ProponoException {
        httpClient.getParams().setAuthenticationPreemptive(true);
        method.setRequestHeader("Authorization", authToken);
    }
}
