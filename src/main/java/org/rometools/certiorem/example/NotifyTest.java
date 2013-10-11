/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.rometools.certiorem.example;

import com.google.inject.Singleton;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.rometools.certiorem.pub.NotificationException;
import org.rometools.certiorem.pub.Publisher;

/**
 *
 * @author robert.cooper
 */
@Singleton
public class NotifyTest extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException{
        Publisher pub = new Publisher();
        try {
            pub.sendUpdateNotification("http://localhost/webapp/hub", "http://localhost/webapp/research-atom.xml");
        } catch (NotificationException ex) {
            Logger.getLogger(NotifyTest.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServletException(ex);
        }
    }
}
