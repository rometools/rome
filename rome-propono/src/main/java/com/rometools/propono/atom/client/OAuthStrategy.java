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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.oauth.OAuth;
import net.oauth.OAuthAccessor;
import net.oauth.OAuthConsumer;
import net.oauth.OAuthMessage;
import net.oauth.OAuthServiceProvider;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.util.ParameterParser;

import com.rometools.propono.utils.ProponoException;

/**
 * Strategy for using OAuth.
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public class OAuthStrategy implements AuthStrategy {

    private State state = State.UNAUTHORIZED;

    private enum State {
        UNAUTHORIZED, // have not sent any requests
        REQUEST_TOKEN, // have a request token
        AUTHORIZED, // are authorized
        ACCESS_TOKEN // have access token, ready to make calls
    }

    private final String username;
    private final String consumerKey;
    private final String consumerSecret;
    private final String keyType;

    private final String reqUrl;
    private final String authzUrl;
    private final String accessUrl;

    private final String nonce;
    private final long timestamp;

    private String requestToken = null;
    private String accessToken = null;
    private String tokenSecret = null;

    /**
     * Create OAuth authentcation strategy and negotiate with services to obtain access token to be
     * used in subsequent calls.
     *
     * @param username Username to be used in authentication
     * @param key Consumer key
     * @param secret Consumer secret
     * @param keyType Key type (e.g. "HMAC-SHA1")
     * @param reqUrl URL of request token service
     * @param authzUrl URL of authorize service
     * @param accessUrl URL of acess token service
     * @throws ProponoException on any sort of initialization error
     */
    public OAuthStrategy(final String username, final String key, final String secret, final String keyType, final String reqUrl, final String authzUrl,
            final String accessUrl) throws ProponoException {

        this.username = username;
        this.reqUrl = reqUrl;
        this.authzUrl = authzUrl;
        this.accessUrl = accessUrl;
        consumerKey = key;
        consumerSecret = secret;
        this.keyType = keyType;

        nonce = UUID.randomUUID().toString();
        timestamp = new Date().getTime() / 1000L;

        init();
    }

    private void init() throws ProponoException {
        callOAuthUri(reqUrl);
        callOAuthUri(authzUrl);
        callOAuthUri(accessUrl);
    }

    @Override
    public void addAuthentication(final HttpClient httpClient, final HttpMethodBase method) throws ProponoException {

        if (state != State.ACCESS_TOKEN) {
            throw new ProponoException("ERROR: authentication strategy failed init");
        }

        // add OAuth name/values to request query string

        // wish we didn't have to parse them apart first, ugh
        List<NameValuePair> originalqlist = null;
        if (method.getQueryString() != null) {
            String qstring = method.getQueryString().trim();
            qstring = qstring.startsWith("?") ? qstring.substring(1) : qstring;
            @SuppressWarnings("unchecked")
            final List<NameValuePair> parameters = new ParameterParser().parse(qstring, '&');
            originalqlist = parameters;
        } else {
            originalqlist = new ArrayList<NameValuePair>();
        }

        // put query string into hashmap form to please OAuth.net classes
        final Map<String, String> params = new HashMap<String, String>();
        for (final Object element : originalqlist) {
            final NameValuePair pair = (NameValuePair) element;
            params.put(pair.getName(), pair.getValue());
        }

        // add OAuth params to query string
        params.put("xoauth_requestor_id", username);
        params.put("oauth_consumer_key", consumerKey);
        params.put("oauth_signature_method", keyType);
        params.put("oauth_timestamp", Long.toString(timestamp));
        params.put("oauth_nonce", nonce);
        params.put("oauth_token", accessToken);
        params.put("oauth_token_secret", tokenSecret);

        // sign complete URI
        String finalUri = null;
        final OAuthServiceProvider provider = new OAuthServiceProvider(reqUrl, authzUrl, accessUrl);
        final OAuthConsumer consumer = new OAuthConsumer(null, consumerKey, consumerSecret, provider);
        final OAuthAccessor accessor = new OAuthAccessor(consumer);
        accessor.tokenSecret = tokenSecret;
        OAuthMessage message;
        try {
            message = new OAuthMessage(method.getName(), method.getURI().toString(), params.entrySet());
            message.sign(accessor);

            finalUri = OAuth.addParameters(message.URL, message.getParameters());

        } catch (final Exception ex) {
            throw new ProponoException("ERROR: OAuth signing request", ex);
        }

        // pull query string off and put it back onto method
        method.setQueryString(finalUri.substring(finalUri.lastIndexOf("?")));
    }

    private void callOAuthUri(final String uri) throws ProponoException {

        final HttpClient httpClient = new HttpClient();

        final HttpMethodBase method;
        final String content;

        final Map<String, String> params = new HashMap<String, String>();
        params.put("oauth_version", "1.0");
        if (username != null) {
            params.put("xoauth_requestor_id", username);
        }
        params.put("oauth_consumer_key", consumerKey);
        params.put("oauth_signature_method", keyType);
        params.put("oauth_timestamp", Long.toString(timestamp));
        params.put("oauth_nonce", nonce);
        params.put("oauth_callback", "none");

        final OAuthServiceProvider provider = new OAuthServiceProvider(reqUrl, authzUrl, accessUrl);
        final OAuthConsumer consumer = new OAuthConsumer(null, consumerKey, consumerSecret, provider);
        final OAuthAccessor accessor = new OAuthAccessor(consumer);

        if (state == State.UNAUTHORIZED) {

            try {
                final OAuthMessage message = new OAuthMessage("GET", uri, params.entrySet());
                message.sign(accessor);

                final String finalUri = OAuth.addParameters(message.URL, message.getParameters());
                method = new GetMethod(finalUri);
                httpClient.executeMethod(method);
                content = method.getResponseBodyAsString();

            } catch (final Exception e) {
                throw new ProponoException("ERROR fetching request token", e);
            }

        } else if (state == State.REQUEST_TOKEN) {

            try {
                params.put("oauth_token", requestToken);
                params.put("oauth_token_secret", tokenSecret);
                accessor.tokenSecret = tokenSecret;

                final OAuthMessage message = new OAuthMessage("POST", uri, params.entrySet());
                message.sign(accessor);

                final String finalUri = OAuth.addParameters(message.URL, message.getParameters());
                method = new PostMethod(finalUri);
                httpClient.executeMethod(method);
                content = method.getResponseBodyAsString();

            } catch (final Exception e) {
                throw new ProponoException("ERROR fetching request token", e);
            }

        } else if (state == State.AUTHORIZED) {

            try {
                params.put("oauth_token", accessToken);
                params.put("oauth_token_secret", tokenSecret);
                accessor.tokenSecret = tokenSecret;

                final OAuthMessage message = new OAuthMessage("GET", uri, params.entrySet());
                message.sign(accessor);

                final String finalUri = OAuth.addParameters(message.URL, message.getParameters());
                method = new GetMethod(finalUri);
                httpClient.executeMethod(method);
                content = method.getResponseBodyAsString();

            } catch (final Exception e) {
                throw new ProponoException("ERROR fetching request token", e);
            }

        } else {
            method = null;
            content = null;
            return;
        }

        String token = null;
        String secret = null;

        if (content != null) {
            final String[] settings = content.split("&");
            for (final String setting2 : settings) {
                final String[] setting = setting2.split("=");
                if (setting.length > 1) {
                    if ("oauth_token".equals(setting[0])) {
                        token = setting[1];
                    } else if ("oauth_token_secret".equals(setting[0])) {
                        secret = setting[1];
                    }
                }
            }
        }

        // TODO review switch without 'default'

        switch (state) {

            case UNAUTHORIZED:
                if (token != null && secret != null) {
                    requestToken = token;
                    tokenSecret = secret;
                    state = State.REQUEST_TOKEN;
                } else {
                    throw new ProponoException("ERROR: requestToken or tokenSecret is null");
                }
                break;

            case REQUEST_TOKEN:
                if (method.getStatusCode() == 200) {
                    state = State.AUTHORIZED;
                } else {
                    throw new ProponoException("ERROR: authorization returned code: " + method.getStatusCode());
                }
                break;

            case AUTHORIZED:
                if (token != null && secret != null) {
                    accessToken = token;
                    tokenSecret = secret;
                    state = State.ACCESS_TOKEN;
                } else {
                    throw new ProponoException("ERROR: accessToken or tokenSecret is null");
                }
                break;
        }
    }
}
