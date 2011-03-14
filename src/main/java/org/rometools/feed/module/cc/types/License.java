/*
 * LicenseEnumeration.java
 *
 * Created on November 20, 2005, 3:20 PM
 *
* This library is provided under dual licenses. 
 * You may choose the terms of the Lesser General Public License or the Apache
 * License at your discretion.
 *
 *  Copyright (C) 2005  Robert Cooper, Temple of the Screaming Penguin
 *
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.   
 */
package org.rometools.feed.module.cc.types;

import com.sun.syndication.feed.impl.EqualsBean;
import com.sun.syndication.feed.impl.ToStringBean;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * @version $Revision: 1.1 $
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class License {
    private static final String CC_START = "http://creativecommons.org/licenses/";
    private static final HashMap lookupLicense = new HashMap();
    public static final License NO_DERIVS = new License("http://creativecommons.org/licenses/nd/1.0/",new Behaviour[0],new Behaviour[] {
	Behaviour.DISTRIBUTION,Behaviour.REPRODUCTION
    });
    public static final License NO_DERIVS_NONCOMMERCIAL = new License("http://creativecommons.org/licenses/nd-nc/1.0/",new Behaviour[] {
	Behaviour.NONCOMMERCIAL
    },new Behaviour[] {Behaviour.DISTRIBUTION,Behaviour.REPRODUCTION});
    public static final License NONCOMMERCIAL = new License("http://creativecommons.org/licenses/nc/1.0/",new Behaviour[] {
	Behaviour.NONCOMMERCIAL
    },new Behaviour[] {
	Behaviour.DERIVATIVE,Behaviour.DISTRIBUTION,Behaviour.REPRODUCTION
    });
    public static final License SHARE_ALIKE = new License("http://creativecommons.org/licenses/sa/1.0/",new Behaviour[] {
	Behaviour.COPYLEFT
    },new Behaviour[] {
	Behaviour.DERIVATIVE,Behaviour.DISTRIBUTION,Behaviour.REPRODUCTION
    });
    public static final License SHARE_ALIKE_NONCOMMERCIAL = new License("http://creativecommons.org/licenses/nc-sa/1.0/",new Behaviour[] {
	Behaviour.COPYLEFT,Behaviour.NONCOMMERCIAL
    },new Behaviour[] {
	Behaviour.DERIVATIVE,Behaviour.DISTRIBUTION,Behaviour.REPRODUCTION
    });
    public static final License SHARE_ALIKE_ATTRIBUTION = new License("http://creativecommons.org/licenses/by-sa/2.5/",new Behaviour[] {
	Behaviour.COPYLEFT,Behaviour.ATTRIBUTION
    },new Behaviour[] {
	Behaviour.DERIVATIVE,Behaviour.DISTRIBUTION,Behaviour.REPRODUCTION
    });
    public static final License SHARE_ALIKE_NONCOMMERCIAL_ATTRIBUTION = new License("http://creativecommons.org/licenses/by-nc-sa/2.5/",new Behaviour[] {
	Behaviour.COPYLEFT,Behaviour.ATTRIBUTION,Behaviour.NONCOMMERCIAL
    },new Behaviour[] {
	Behaviour.DERIVATIVE,Behaviour.DISTRIBUTION,Behaviour.REPRODUCTION
    });
    public static final License NONCOMMERCIAL_ATTRIBUTION = new License("http://creativecommons.org/licenses/by-nc/2.5/",new Behaviour[] {
	Behaviour.ATTRIBUTION,Behaviour.NONCOMMERCIAL
    },new Behaviour[] {
	Behaviour.DERIVATIVE,Behaviour.DISTRIBUTION,Behaviour.REPRODUCTION
    });
    public static final License NONCOMMERCIAL_ATTRIBUTION_NO_DERIVS = new License("http://creativecommons.org/licenses/by-nc-nd/2.5/",new Behaviour[] {
	Behaviour.ATTRIBUTION,Behaviour.NONCOMMERCIAL
    },new Behaviour[] {Behaviour.DISTRIBUTION,Behaviour.REPRODUCTION});
    public static final License ATTRIBUTION_NO_DERIVS = new License("http://creativecommons.org/licenses/by-nd/2.5/",new Behaviour[] {
	Behaviour.ATTRIBUTION
    },new Behaviour[] {Behaviour.DISTRIBUTION,Behaviour.REPRODUCTION});
    public static final License ATTRIBUTION = new License("http://creativecommons.org/licenses/by/2.5/",new Behaviour[] {
	Behaviour.ATTRIBUTION
    },new Behaviour[] {
	Behaviour.DERIVATIVE,Behaviour.DISTRIBUTION,Behaviour.REPRODUCTION
    });
    private String uri;
    private Behaviour[] permits;
    private Behaviour[] requires;
    
    /**
     * Creates a new instance of License
     */
    public License(String uri,Behaviour[] requires,Behaviour[] permits) {
	this.requires = requires;
	this.permits = permits;
	this.uri = uri;
	License.lookupLicense.put(uri,this);
	
	if(this.uri.endsWith("/")) {
	    //System.out.println(uri.substring(0,this.uri.lastIndexOf("/")));
	    License.lookupLicense.put(uri.substring(0,this.uri.lastIndexOf("/")),this);
	}
    }
    
    public static License findByValue(String uri) {
	License found = (License)License.lookupLicense.get(uri);
	
	//No I am going to try an guess about unknown licenses
	// This is try and match known CC licenses of other versions or various URLs to
	// current licenses, then make a new one with the same permissions.
	if(found == null && uri.startsWith("http://") && uri.toLowerCase().indexOf("creativecommons.org") != -1) {
	    Iterator it = License.lookupLicense.keySet().iterator();
	    while(it.hasNext()&&(found == null)) {
		try{
		    String key = (String)it.next();
		    if(key.startsWith( CC_START ) ){
			String licensePath = key.substring( CC_START.length(), key.length() );
			StringTokenizer tok = new StringTokenizer( licensePath, "/");
			String license = tok.nextToken();
			String version = tok.nextToken();
			if( uri.toLowerCase().indexOf("creativecommons.org/licenses/"+license) != -1){
			    License current = (License) lookupLicense.get( key );
			    found = new License( uri, current.getRequires(), current.getPermits() );
			}
		    }
		} catch(Exception e){
		    e.printStackTrace();
		}
	    }
	}
	//OK, we got here. If we haven't found a match, return a new License with unknown permissions.
	if( found == null ){
	    found = new License( uri, null, null );
	}
	return found;
    }
    
    public Behaviour[] getPermits() {
	return this.permits;
    }
    
    public Behaviour[] getRequires() {
	return this.requires;
    }
    public String toString() {
	
	ToStringBean tsb = new ToStringBean( License.class, this );
	return tsb.toString();
    }
    public String getValue() {
	return this.uri;
    }
    public boolean equals(Object obj) {
	EqualsBean eBean = new EqualsBean(License.class,this);
	return eBean.beanEquals(obj);
    }
    
    public int hashCode() {
	EqualsBean equals = new EqualsBean(License.class,this);
	return equals.beanHashCode();
    }
    public static class Behaviour {
	private static final HashMap lookup = new HashMap();
	public static final Behaviour REPRODUCTION = new Behaviour("http://web.resource.org/cc/Reproduction");
	public static final Behaviour DISTRIBUTION = new Behaviour("http://web.resource.org/cc/Distribution");
	public static final Behaviour DERIVATIVE = new Behaviour("http://web.resource.org/cc/DerivativeWorks");
	public static final Behaviour NOTICE = new Behaviour("http://web.resource.org/cc/Notice");
	public static final Behaviour ATTRIBUTION = new Behaviour("http://web.resource.org/cc/Attribution");
	public static final Behaviour COPYLEFT = new Behaviour("http://web.resource.org/cc/Copyleft");
	public static final Behaviour NONCOMMERCIAL = new Behaviour("http://web.resource.org/cc/Noncommercial");
	private String uri;
	
	private Behaviour(String uri) {
	    this.uri = uri;
	    Behaviour.lookup.put(uri,this);
	}
	
	public static Behaviour findByValue(String uri) {
	    return (Behaviour)Behaviour.lookup.get(uri);
	}
	
	public String toString() {
	    return this.uri;
	}
    }
}
