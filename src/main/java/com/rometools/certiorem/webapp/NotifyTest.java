/*
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
 */
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
