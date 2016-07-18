/**
 *
 *  Copyright (C) The ROME Team  2011
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.rometools.certiorem.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rometools.certiorem.HttpStatusCodeException;
import com.rometools.certiorem.sub.Subscriptions;

/**
 *
 * @author robert.cooper
 * @deprecated Certiorem will be removed in Rome 2.
 */
@Deprecated
public abstract class AbstractSubServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final Subscriptions subscriptions;

    protected AbstractSubServlet(final Subscriptions subscriptions) {
        this.subscriptions = subscriptions;
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final String mode = req.getParameter("hub.mode");
        final String topic = req.getParameter("hub.topic");
        final String challenge = req.getParameter("hub.challenge");
        final String leaseString = req.getParameter("hub.lease_seconds");
        final String verifyToken = req.getParameter("hub.verify_token");
        try {
            final String result = subscriptions.validate(req.getRequestURL().toString(), topic, mode, challenge, leaseString, verifyToken);
            resp.setStatus(200);
            resp.getWriter().print(result);
        } catch (final HttpStatusCodeException e) {
            e.printStackTrace();
            resp.setStatus(e.getStatus());
            resp.getWriter().print(e.getMessage());
        }
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        try {
            final String requestUrl = req.getRequestURL().toString();
            final ServletInputStream inputStream = req.getInputStream();
            subscriptions.callback(requestUrl, inputStream);
        } catch (final HttpStatusCodeException e) {
            e.printStackTrace();
            resp.setStatus(e.getStatus());
            resp.getWriter().println(e.getMessage());
        }
    }

}
