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


package org.rometools.certiorem.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpUtils;
import org.rometools.certiorem.HttpStatusCodeException;
import org.rometools.certiorem.sub.Subscriptions;

/**
 *
 * @author robert.cooper
 */
public class AbstractSubServlet extends HttpServlet {

    private final Subscriptions subscriptions;
    
    protected AbstractSubServlet(final Subscriptions subscriptions){
        super();
        this.subscriptions = subscriptions;
      
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mode = req.getParameter("hub.mode");
        String topic = req.getParameter("hub.topic");
        String challenge = req.getParameter("hub.challenge");
        String leaseString = req.getParameter("hub.lease_seconds");
        String verifyToken = req.getParameter("hub.verify_token");
        try{
            String result = subscriptions.validate(HttpUtils.getRequestURL(req).toString(), topic, mode, challenge, leaseString, verifyToken);
            resp.setStatus(200);
            resp.getWriter().print(result);
            return;
        } catch(HttpStatusCodeException e){
            e.printStackTrace();
            resp.setStatus(e.getStatus());
            resp.getWriter().print(e.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            this.subscriptions.callback(HttpUtils.getRequestURL(req).toString(), req.getInputStream());
            return;
        } catch(HttpStatusCodeException e){
            e.printStackTrace();
            resp.setStatus(e.getStatus());
            resp.getWriter().println(e.getMessage());
        }
    }



}
