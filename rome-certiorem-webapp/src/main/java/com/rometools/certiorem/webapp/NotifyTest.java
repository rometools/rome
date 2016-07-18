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
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rometools.certiorem.webapp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Singleton;
import com.rometools.certiorem.pub.NotificationException;
import com.rometools.certiorem.pub.Publisher;

/**
 *
 * @author robert.cooper
 * @deprecated Certiorem will be removed in Rome 2.
 */
@Deprecated
@Singleton
public class NotifyTest extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(NotifyTest.class);

    @Override
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException {
        final Publisher pub = new Publisher();
        try {
            pub.sendUpdateNotification("http://localhost/webapp/hub", "http://localhost/webapp/research-atom.xml");
        } catch (final NotificationException ex) {
            LOG.error(null, ex);
            throw new ServletException(ex);
        }
    }

}
