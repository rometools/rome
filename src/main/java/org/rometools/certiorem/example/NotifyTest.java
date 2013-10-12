/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.rometools.certiorem.example;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rometools.certiorem.pub.NotificationException;
import org.rometools.certiorem.pub.Publisher;

import com.google.inject.Singleton;

/**
 * 
 * @author robert.cooper
 */
@Singleton
public class NotifyTest extends HttpServlet {

    @Override
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException {
        final Publisher pub = new Publisher();
        try {
            pub.sendUpdateNotification("http://localhost/webapp/hub", "http://localhost/webapp/research-atom.xml");
        } catch (final NotificationException ex) {
            Logger.getLogger(NotifyTest.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServletException(ex);
        }
    }
}
