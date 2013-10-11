/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.rometools.certiorem.example;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import org.rometools.certiorem.sub.Subscriptions;
import org.rometools.certiorem.web.AbstractSubServlet;

/**
 *
 * @author robert.cooper
 */
@Singleton
public class SubServlet extends AbstractSubServlet {

    @Inject
    public SubServlet(final Subscriptions subscriptions){
        super(subscriptions);
    }

}
