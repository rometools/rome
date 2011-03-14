/*
 * CustomTagGenerator.java
 *
 * Created on February 6, 2006, 1:08 AM
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
 */
package org.rometools.feed.module.base.io;

import com.sun.syndication.feed.module.Module;
import org.rometools.feed.module.base.CustomTag;
import org.rometools.feed.module.base.CustomTagImpl;
import org.rometools.feed.module.base.CustomTags;
import org.rometools.feed.module.base.types.DateTimeRange;
import org.rometools.feed.module.base.types.FloatUnit;
import org.rometools.feed.module.base.types.IntUnit;
import org.rometools.feed.module.base.types.ShortDate;
import com.sun.syndication.io.ModuleGenerator;

import org.jdom.Element;

import java.net.URL;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;


/**
 * @version $Revision: 1.1 $
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class CustomTagGenerator implements ModuleGenerator {
    static final HashSet NAMESPACES = new HashSet();

    static {
        NAMESPACES.add(CustomTagParser.NS);
    }

    /** Creates a new instance of CustomTagGenerator */
    public CustomTagGenerator() {
    }

    public String getNamespaceUri() {
        return CustomTags.URI;
    }

    public java.util.Set getNamespaces() {
        return NAMESPACES;
    }

    public void generate(Module module,Element element) {
        if(!(module instanceof CustomTags)) {
            return;
        }

        List tags = ((CustomTags)module).getValues();
        Iterator it = tags.iterator();

        while(it.hasNext()) {
            CustomTag tag = (CustomTag)it.next();

            if(tag.getValue() instanceof DateTimeRange) {
                DateTimeRange dtr = (DateTimeRange)tag.getValue();
                Element newTag = new Element(tag.getName(),CustomTagParser.NS);
                newTag.setAttribute("type","dateTimeRange");
                newTag.addContent(this.generateSimpleElement("start",GoogleBaseParser.LONG_DT_FMT.format(dtr.getStart())));
                newTag.addContent(this.generateSimpleElement("end",GoogleBaseParser.LONG_DT_FMT.format(dtr.getEnd())));
                element.addContent(newTag);
            } else if(tag.getValue() instanceof ShortDate) {
                ShortDate sd = (ShortDate)tag.getValue();
                Element newTag = this.generateSimpleElement(tag.getName(),GoogleBaseParser.SHORT_DT_FMT.format(sd));
                newTag.setAttribute("type","date");
                element.addContent(newTag);
            } else if(tag.getValue() instanceof Date) {
                Date d = (Date)tag.getValue();
                Element newTag = this.generateSimpleElement(tag.getName(),GoogleBaseParser.SHORT_DT_FMT.format(d));
                newTag.setAttribute("type","dateTime");
                element.addContent(newTag);
            } else if(tag.getValue() instanceof Integer) {
                Element newTag = this.generateSimpleElement(tag.getName(),tag.getValue().toString());
                newTag.setAttribute("type","int");
                element.addContent(newTag);
            } else if(tag.getValue() instanceof IntUnit) {
                Element newTag = this.generateSimpleElement(tag.getName(),tag.getValue().toString());
                newTag.setAttribute("type","intUnit");
                element.addContent(newTag);
            } else if(tag.getValue() instanceof Float) {
                Element newTag = this.generateSimpleElement(tag.getName(),tag.getValue().toString());
                newTag.setAttribute("type","float");
                element.addContent(newTag);
            } else if(tag.getValue() instanceof FloatUnit) {
                Element newTag = this.generateSimpleElement(tag.getName(),tag.getValue().toString());
                newTag.setAttribute("type","floatUnit");
                element.addContent(newTag);
            } else if(tag.getValue() instanceof String) {
                Element newTag = this.generateSimpleElement(tag.getName(),tag.getValue().toString());
                newTag.setAttribute("type","string");
                element.addContent(newTag);
            } else if(tag.getValue() instanceof URL) {
                Element newTag = this.generateSimpleElement(tag.getName(),tag.getValue().toString());
                newTag.setAttribute("type","url");
                element.addContent(newTag);
            } else if(tag.getValue() instanceof Boolean) {
                Element newTag = this.generateSimpleElement(tag.getName(),tag.getValue().toString());
                newTag.setAttribute("type","boolean");
                element.addContent(newTag);
            } else if( tag.getValue() instanceof CustomTagImpl.Location ){
		Element newTag = this.generateSimpleElement(tag.getName(),tag.getValue().toString());
                newTag.setAttribute("type","location");
                element.addContent(newTag);
	    }
        }
    }

    protected Element generateSimpleElement(String name,String value) {
        Element element = new Element(name,CustomTagParser.NS);
        element.addContent(value);

        return element;
    }
}
