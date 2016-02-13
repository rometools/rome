/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rometools.certiorem.webapp;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.rometools.certiorem.sub.Subscriptions;
import com.rometools.certiorem.web.AbstractSubServlet;

/**
 *
 * @author robert.cooper
 */
@Singleton
public class SubServlet extends AbstractSubServlet {

    private static final long serialVersionUID = 1L;

    @Inject
    public SubServlet(final Subscriptions subscriptions) {
        super(subscriptions);
    }

}
