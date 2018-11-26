/*
 * Copyright 2005 Robert Cooper, Temple of the Screaming Penguin
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
 */
package com.rometools.modules.cc.types;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.rome.feed.impl.EqualsBean;
import com.rometools.rome.feed.impl.ToStringBean;

public class License {

    private static final String CC_START = "http://creativecommons.org/licenses/";
    private static final Map<String, License> lookupLicense = new ConcurrentHashMap<String, License>();
    private static final Logger LOG = LoggerFactory.getLogger(License.class);
    public static final License NO_DERIVS = new License("http://creativecommons.org/licenses/nd/1.0/", new Behaviour[0], new Behaviour[] {
            Behaviour.DISTRIBUTION, Behaviour.REPRODUCTION});
    public static final License NO_DERIVS_NONCOMMERCIAL = new License("http://creativecommons.org/licenses/nd-nc/1.0/",
            new Behaviour[] {Behaviour.NONCOMMERCIAL}, new Behaviour[] {Behaviour.DISTRIBUTION, Behaviour.REPRODUCTION});
    public static final License NONCOMMERCIAL = new License("http://creativecommons.org/licenses/nc/1.0/", new Behaviour[] {Behaviour.NONCOMMERCIAL},
            new Behaviour[] {Behaviour.DERIVATIVE, Behaviour.DISTRIBUTION, Behaviour.REPRODUCTION});
    public static final License SHARE_ALIKE = new License("http://creativecommons.org/licenses/sa/1.0/", new Behaviour[] {Behaviour.COPYLEFT}, new Behaviour[] {
            Behaviour.DERIVATIVE, Behaviour.DISTRIBUTION, Behaviour.REPRODUCTION});
    public static final License SHARE_ALIKE_NONCOMMERCIAL = new License("http://creativecommons.org/licenses/nc-sa/1.0/", new Behaviour[] {Behaviour.COPYLEFT,
            Behaviour.NONCOMMERCIAL}, new Behaviour[] {Behaviour.DERIVATIVE, Behaviour.DISTRIBUTION, Behaviour.REPRODUCTION});
    public static final License SHARE_ALIKE_ATTRIBUTION = new License("http://creativecommons.org/licenses/by-sa/2.5/", new Behaviour[] {Behaviour.COPYLEFT,
            Behaviour.ATTRIBUTION}, new Behaviour[] {Behaviour.DERIVATIVE, Behaviour.DISTRIBUTION, Behaviour.REPRODUCTION});
    public static final License SHARE_ALIKE_NONCOMMERCIAL_ATTRIBUTION = new License("http://creativecommons.org/licenses/by-nc-sa/2.5/", new Behaviour[] {
            Behaviour.COPYLEFT, Behaviour.ATTRIBUTION, Behaviour.NONCOMMERCIAL}, new Behaviour[] {Behaviour.DERIVATIVE, Behaviour.DISTRIBUTION,
            Behaviour.REPRODUCTION});
    public static final License NONCOMMERCIAL_ATTRIBUTION = new License("http://creativecommons.org/licenses/by-nc/2.5/", new Behaviour[] {
            Behaviour.ATTRIBUTION, Behaviour.NONCOMMERCIAL}, new Behaviour[] {Behaviour.DERIVATIVE, Behaviour.DISTRIBUTION, Behaviour.REPRODUCTION});
    public static final License NONCOMMERCIAL_ATTRIBUTION_NO_DERIVS = new License("http://creativecommons.org/licenses/by-nc-nd/2.5/", new Behaviour[] {
            Behaviour.ATTRIBUTION, Behaviour.NONCOMMERCIAL}, new Behaviour[] {Behaviour.DISTRIBUTION, Behaviour.REPRODUCTION});
    public static final License ATTRIBUTION_NO_DERIVS = new License("http://creativecommons.org/licenses/by-nd/2.5/", new Behaviour[] {Behaviour.ATTRIBUTION},
            new Behaviour[] {Behaviour.DISTRIBUTION, Behaviour.REPRODUCTION});
    public static final License ATTRIBUTION = new License("http://creativecommons.org/licenses/by/2.5/", new Behaviour[] {Behaviour.ATTRIBUTION},
            new Behaviour[] {Behaviour.DERIVATIVE, Behaviour.DISTRIBUTION, Behaviour.REPRODUCTION});
    private final String uri;
    private final Behaviour[] permits;
    private final Behaviour[] requires;

    public License(final String uri, final Behaviour[] requires, final Behaviour[] permits) {
        this.requires = requires;
        this.permits = permits;
        this.uri = uri;
        License.lookupLicense.put(uri, this);

        if (this.uri.endsWith("/")) {
            License.lookupLicense.put(uri.substring(0, this.uri.lastIndexOf("/")), this);
        }
    }

    public static License findByValue(final String uri) {
        License found = License.lookupLicense.get(uri);

        // No I am going to try an guess about unknown licenses
        // This is try and match known CC licenses of other versions or various URLs to
        // current licenses, then make a new one with the same permissions.
        if (found == null && uri.startsWith("http://") && uri.toLowerCase().indexOf("creativecommons.org") != -1) {
            final Iterator<String> it = License.lookupLicense.keySet().iterator();
            while (it.hasNext() && found == null) {
                final String key = it.next();
                try {
                    if (key.startsWith(CC_START)) {
                        final String licensePath = key.substring(CC_START.length(), key.length());
                        final StringTokenizer tok = new StringTokenizer(licensePath, "/");
                        final String license = tok.nextToken();
                        // final String version = tok.nextToken();
                        if (uri.toLowerCase().indexOf("creativecommons.org/licenses/" + license) != -1) {
                            final License current = lookupLicense.get(key);
                            found = new License(uri, current.getRequires(), current.getPermits());
                        }
                    }
                } catch (final Exception e) {
                    LOG.error("Error", e);
                }
            }
        }
        // OK, we got here. If we haven't found a match, return a new License with unknown
        // permissions.
        if (found == null) {
            found = new License(uri, null, null);
        }
        return found;
    }

    /**
     * This is just useful for testing to allow clearing of the looked up licenses.
     */
    static void clear() {
        lookupLicense.clear();
    }

    public Behaviour[] getPermits() {
        return permits;
    }

    public Behaviour[] getRequires() {
        return requires;
    }

    @Override
    public String toString() {
        return ToStringBean.toString(License.class, this);
    }

    public String getValue() {
        return uri;
    }

    @Override
    public boolean equals(final Object obj) {
        return EqualsBean.beanEquals(License.class, this, obj);
    }

    @Override
    public int hashCode() {
        return EqualsBean.beanHashCode(this);
    }

    public static class Behaviour {
        private static final Map<String, Behaviour> lookup = new HashMap<String, Behaviour>();
        public static final Behaviour REPRODUCTION = new Behaviour("http://web.resource.org/cc/Reproduction");
        public static final Behaviour DISTRIBUTION = new Behaviour("http://web.resource.org/cc/Distribution");
        public static final Behaviour DERIVATIVE = new Behaviour("http://web.resource.org/cc/DerivativeWorks");
        public static final Behaviour NOTICE = new Behaviour("http://web.resource.org/cc/Notice");
        public static final Behaviour ATTRIBUTION = new Behaviour("http://web.resource.org/cc/Attribution");
        public static final Behaviour COPYLEFT = new Behaviour("http://web.resource.org/cc/Copyleft");
        public static final Behaviour NONCOMMERCIAL = new Behaviour("http://web.resource.org/cc/Noncommercial");
        private final String uri;

        private Behaviour(final String uri) {
            this.uri = uri;
            Behaviour.lookup.put(uri, this);
        }

        public static Behaviour findByValue(final String uri) {
            return Behaviour.lookup.get(uri);
        }

        @Override
        public String toString() {
            return uri;
        }
    }
}
