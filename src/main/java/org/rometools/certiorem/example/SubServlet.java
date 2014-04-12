/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.rometools.certiorem.example;

import org.rometools.certiorem.sub.Subscriptions;
import org.rometools.certiorem.web.AbstractSubServlet;

import com.google.inject.Inject;
import com.google.inject.Singleton;

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
