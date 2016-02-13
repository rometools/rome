/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rometools.certiorem.webapp;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.rometools.certiorem.hub.Hub;
import com.rometools.certiorem.web.AbstractHubServlet;

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
