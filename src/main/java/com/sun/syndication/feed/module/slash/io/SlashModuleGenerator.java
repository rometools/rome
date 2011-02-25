/*
 * SlashModuleGenerator.java
 *
 * Created on November 19, 2005, 9:07 PM
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

package com.sun.syndication.feed.module.slash.io;

import com.sun.syndication.feed.module.Module;
import com.sun.syndication.io.ModuleGenerator;
import com.sun.syndication.feed.module.slash.Slash;
import java.util.HashSet;
import org.jdom.Element;
import org.jdom.Namespace;

/** The ModuleGenerator implementation for the Slash plug in.
 * @version $Revision: 1.1 $
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class SlashModuleGenerator implements ModuleGenerator {
    
    private static final Namespace NS = Namespace.getNamespace("slash", Slash.URI);
    
    /** Creates a new instance of SlashModuleGenerator */
    public SlashModuleGenerator() {
    }
    
    public void generate(Module module, Element element) {
	if( !( module instanceof Slash) )
	    return;
	Slash slash = (Slash) module;
	if(slash.getComments() != null ){
	    element.addContent( this.generateSimpleElement("comments", slash.getComments().toString()));	    
	}
	if(slash.getDepartment() != null){
	    element.addContent( this.generateSimpleElement("department", slash.getDepartment()));	    
	}
	if(slash.getSection() != null){
	    element.addContent( this.generateSimpleElement("section", slash.getSection()));	    
	}
	if(slash.getHitParade() != null && slash.getHitParade().length > 0 ){
	    StringBuffer buff = new StringBuffer();
	    Integer[] p = slash.getHitParade();
	    for(int i=0; i < p.length; i++){
		if(i!= 0)
		    buff.append(",");
		buff.append( p[i] );
	    }
	    element.addContent(this.generateSimpleElement("hit_parade", buff.toString()));
	}
	
    }
    
    protected Element generateSimpleElement(String name,String value) {
	Element element = new Element(name,SlashModuleGenerator.NS);
	element.addContent(value);	
	return element;
    }
    public java.util.Set getNamespaces() {
	HashSet set = new HashSet();
	set.add( SlashModuleGenerator.NS );
	return set;
    }
    
    public String getNamespaceUri() {
	return Slash.URI;
    }
    
}
