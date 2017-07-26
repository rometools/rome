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

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.rometools.certiorem.sub.Subscriptions;
import com.rometools.certiorem.web.AbstractSubServlet;

/**
 *
 * @author robert.cooper
 * @deprecated Certiorem will be removed in Rome 2.
 */
@Deprecated
@Singleton
public class SubServlet extends AbstractSubServlet {

    private static final long serialVersionUID = 1L;

    @Inject
    public SubServlet(final Subscriptions subscriptions) {
        super(subscriptions);
    }

}
