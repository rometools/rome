/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.rometools.certiorem.example;

import org.rometools.certiorem.hub.Hub;
import org.rometools.certiorem.web.AbstractHubServlet;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 *
 * @author robert.cooper
 */
@Singleton
public class HubServlet extends AbstractHubServlet {

    private static final long serialVersionUID = 1L;

    @Inject
    public HubServlet(final Hub hub) {
        super(hub);
    }

}
