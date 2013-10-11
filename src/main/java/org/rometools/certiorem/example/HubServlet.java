/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.rometools.certiorem.example;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.rometools.certiorem.hub.Hub;
import org.rometools.certiorem.web.AbstractHubServlet;

/**
 *
 * @author robert.cooper
 */
@Singleton
public class HubServlet extends AbstractHubServlet {

    @Inject
    public HubServlet(final Hub hub){
        super(hub);
    }
}
