/*   
 *  Copyright 2007 Dave Johnson (Blogapps project)
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
package org.rometools.propono.blogclient.metaweblog;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.rometools.propono.blogclient.BlogClientException;
import org.rometools.propono.blogclient.BaseBlogEntry;
import org.rometools.propono.blogclient.BlogEntry;
import java.util.Map;


/** 
 * MetaWeblog API implementation of an entry.
 */
public class MetaWeblogEntry extends BaseBlogEntry {
    
    MetaWeblogEntry(MetaWeblogBlog blog, Map entryMap) {
        super(blog);
        id = (String)entryMap.get("postid");
        
        content = new Content((String)entryMap.get("description"));
        
        // let's pretend MetaWeblog API has a content-type
        content.setType("application/metaweblog+xml");
        
        // no way to tell if entry is draft or not
        draft = false;

        title = (String)entryMap.get("title");
        publicationDate = (Date)entryMap.get("dateCreated");        
        permalink = (String)entryMap.get("permaLink");

        // AlexisMP: fix to get the author value populated.
        author.setName( (String)entryMap.get("userid") );
        author.setEmail( (String)entryMap.get("author") );

        categories = new ArrayList();
        Object[] catArray = (Object[])entryMap.get("categories");
        if (catArray != null) {
            for (int i=0; i<catArray.length; i++) {
                Category cat = new Category((String)catArray[i]);
                categories.add(cat);
            }
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public String getToken() {
        return id;
    }
    
    /**
     * True if tokens are equal
     */
    public boolean equals(Object o) {
        if (o instanceof MetaWeblogEntry) {
            MetaWeblogEntry other = (MetaWeblogEntry)o;
            if (other.id != null && id != null) {
                return other.id.equals(id);
            }
        }
        return false;
    }
        
    /**
     * {@inheritDoc}
     */
    public void save() throws BlogClientException {
        id = ((MetaWeblogBlog)getBlog()).saveEntry(this);
    }

    /**
     * {@inheritDoc}
     */
    public void delete() throws BlogClientException {
        ((MetaWeblogBlog)getBlog()).deleteEntry(id);
    }

    HashMap toPostStructure() {
        HashMap struct = new HashMap();       
        if (getTitle() != null) {
            struct.put("title", getTitle());
        }
        if (getContent() != null && getContent().getValue() != null) {
            struct.put("description", getContent().getValue());
        }
        if (getCategories() != null && getCategories().size() > 0) {
            List catArray = new ArrayList();
            List cats = getCategories();
            for (int i=0; i<cats.size(); i++) {
                BlogEntry.Category cat = (BlogEntry.Category)cats.get(i);
                catArray.add(cat.getName());
            }
            struct.put("categories", catArray);
        }
        if (getPublicationDate() != null) {
            struct.put("dateCreated", getPublicationDate());
        }
        if (getId() != null) {
            struct.put("postid", getId());                        
        }
        return struct;
    }
}
