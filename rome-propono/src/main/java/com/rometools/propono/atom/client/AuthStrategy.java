/*
 * Copyright 2007 Dave Johnson (Blogapps project)
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

import com.rometools.propono.utils.ProponoException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;

/**
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public interface AuthStrategy {

    /**
     * Add authentication credenticals, tokens, etc. to HTTP method
     */
    void addAuthentication(HttpClient httpClient, HttpMethodBase method) throws ProponoException;
}
