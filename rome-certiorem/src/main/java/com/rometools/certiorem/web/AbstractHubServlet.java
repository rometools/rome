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
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rometools.certiorem.HttpStatusCodeException;
import com.rometools.certiorem.hub.Hub;

/**
 *
 * @author robert.cooper
 * @deprecated Certiorem will be removed in Rome 2.
 */
@Deprecated
public abstract class AbstractHubServlet extends HttpServlet {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static final String HUBMODE = "hub.mode";
    private final Hub hub;

    protected AbstractHubServlet(final Hub hub) {
        super();
        this.hub = hub;
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        try {
            if ("publish".equals(req.getParameter(HUBMODE))) {
                hub.sendNotification(req.getServerName(), req.getParameter("hub.url"));
            } else {
                final String callback = req.getParameter("hub.callback");
                final String topic = req.getParameter("hub.topic");
                final String[] verifies = req.getParameterValues("hub.verify");
                final String leaseString = req.getParameter("hub.lease_seconds");
                final String secret = req.getParameter("hub.secret");
                final String verifyToken = req.getParameter("hub.verify_token");
                final String verifyMode = Arrays.asList(verifies).contains("async") ? "async" : "sync";
                Boolean result = null;

                if ("subscribe".equals(req.getParameter(HUBMODE))) {
                    final long leaseSeconds = leaseString != null ? Long.parseLong(leaseString) : -1;
                    result = hub.subscribe(callback, topic, verifyMode, leaseSeconds, secret, verifyToken);
                } else if ("unsubscribe".equals(req.getParameter(HUBMODE))) {
                    result = hub.unsubscribe(callback, topic, verifyMode, secret, verifyToken);
                }

                if (result != null && !result) {
                    throw new HttpStatusCodeException(500, "Operation failed.", null);
                }
            }
        } catch (final HttpStatusCodeException sc) {
            resp.setStatus(sc.getStatus());
            resp.getWriter().println(sc.getMessage());

            return;
        }

        resp.setStatus(204);
    }
}
