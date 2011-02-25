/*
 * Copyright 2006 Nathanial X. Freitas, openvision.tv
 *
 * This code is currently released under the Mozilla Public License.
 * http://www.mozilla.org/MPL/
 *
 * Alternately you may apply the terms of the Apache Software License
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
 *
 */
package com.sun.syndication.feed.module.mediarss.io;

import com.sun.syndication.feed.module.Module;
import com.sun.syndication.feed.module.mediarss.MediaEntryModuleImpl;
import com.sun.syndication.feed.module.mediarss.MediaModule;
import com.sun.syndication.feed.module.mediarss.MediaModuleImpl;
import com.sun.syndication.feed.module.mediarss.types.Category;
import com.sun.syndication.feed.module.mediarss.types.Credit;
import com.sun.syndication.feed.module.mediarss.types.Expression;
import com.sun.syndication.feed.module.mediarss.types.Hash;
import com.sun.syndication.feed.module.mediarss.types.MediaContent;
import com.sun.syndication.feed.module.mediarss.types.MediaGroup;
import com.sun.syndication.feed.module.mediarss.types.Metadata;
import com.sun.syndication.feed.module.mediarss.types.PlayerReference;
import com.sun.syndication.feed.module.mediarss.types.Rating;
import com.sun.syndication.feed.module.mediarss.types.Restriction;
import com.sun.syndication.feed.module.mediarss.types.Text;
import com.sun.syndication.feed.module.mediarss.types.Thumbnail;
import com.sun.syndication.feed.module.mediarss.types.Time;
import com.sun.syndication.feed.module.mediarss.types.UrlReference;
import com.sun.syndication.io.ModuleParser;
import com.sun.syndication.io.impl.NumberParser;

import java.net.URI;

import org.jdom.Element;
import org.jdom.Namespace;


import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author Nathanial X. Freitas
 *
 */
public class MediaModuleParser implements ModuleParser {
    private static final Logger LOG = Logger.getLogger(MediaModuleParser.class.getName());
    
    /*
     * Namespace instance for this URI.
     */
    private static final Namespace NS = Namespace.getNamespace(MediaModule.URI);
    
    /* (non-Javadoc)
     * @see com.sun.syndication.io.ModuleParser#getNamespaceUri()
     */
    public String getNamespaceUri() {
        // TODO Auto-generated method stub
        return MediaModule.URI;
    }
    
    /* (non-Javadoc)
     * @see com.sun.syndication.io.ModuleParser#parse(org.jdom.Element)
     */
    public Module parse(Element mmRoot) {
        MediaModuleImpl mod = null;
        
        if (mmRoot.getName().equals("channel") ||
                mmRoot.getName().equals("feed")) {
            mod = new MediaModuleImpl();
        } else {
            mod = new MediaEntryModuleImpl();
        }
        
        mod.setMetadata(parseMetadata(mmRoot));
        mod.setPlayer(parsePlayer(mmRoot));
        
        if (mod instanceof MediaEntryModuleImpl) {
            MediaEntryModuleImpl m = (MediaEntryModuleImpl) mod;
            m.setMediaContents(parseContent(mmRoot));
            m.setMediaGroups(parseGroup(mmRoot));
        }
        
        return mod;
    }
    
    private MediaContent[] parseContent(Element e) {
        List contents = e.getChildren("content", getNS());
        ArrayList values = new ArrayList();
        
        try {
            for (int i = 0; (contents != null) && (i < contents.size()); i++) {
                Element content = (Element) contents.get(i);
                MediaContent mc = null;
                
                if (content.getAttributeValue("url") != null) {
                    try{
                        mc = new MediaContent(new UrlReference(
                                new URI(content.getAttributeValue("url"))));
                        mc.setPlayer(parsePlayer(content));
                    } catch (Exception ex) {
                        LOG.log(Level.WARNING, "Exception parsing content tag.", ex);
                    }
                } else {
                    mc = new MediaContent(parsePlayer(content));
                }
                if (mc != null) {
                    values.add(mc);
                    try{
                        mc.setAudioChannels((content.getAttributeValue("channels") == null)
                        ? null : new Integer(content.getAttributeValue("channels")));
                    } catch (Exception ex) {
                        LOG.log(Level.WARNING, "Exception parsing content tag.", ex);
                    }
                    try{
                        mc.setBitrate((content.getAttributeValue("bitrate") == null)
                        ? null : new Float(content.getAttributeValue("bitrate")));
                    } catch (Exception ex) {
                        LOG.log(Level.WARNING, "Exception parsing content tag.", ex);
                    }
                    try{
                        mc.setDuration((content.getAttributeValue("duration") == null)
                        ? null : new Long(content.getAttributeValue("duration")));
                    } catch (Exception ex) {
                        LOG.log(Level.WARNING, "Exception parsing content tag.", ex);
                    }

                    mc.setMedium(content.getAttributeValue("medium"));

                    String expression = content.getAttributeValue("expression");
                    
                    if (expression != null) {
                        if (expression.equalsIgnoreCase("full")) {
                            mc.setExpression(Expression.FULL);
                        } else if (expression.equalsIgnoreCase("sample")) {
                            mc.setExpression(Expression.SAMPLE);
                        } else if (expression.equalsIgnoreCase("nonstop")) {
                            mc.setExpression(Expression.NONSTOP);
                        }
                    }
                    
                    try{
                        mc.setFileSize((content.getAttributeValue("fileSize") == null)
                        ? null : NumberParser.parseLong(content.getAttributeValue("fileSize")));
                    } catch (Exception ex) {
                        LOG.log(Level.WARNING, "Exception parsing content tag.", ex);
                    }
                    try{
                        mc.setFramerate((content.getAttributeValue("framerate") == null)
                        ? null : NumberParser.parseFloat(content.getAttributeValue("framerate")));
                    } catch (Exception ex) {
                        LOG.log(Level.WARNING, "Exception parsing content tag.", ex);
                    }
                    try {                	
                        mc.setHeight((content.getAttributeValue("height") == null)
                        ? null : NumberParser.parseInt(content.getAttributeValue("height")));
                    } catch (Exception ex) {
                        LOG.log(Level.WARNING, "Exception parsing content tag.", ex);
                    }
                    
                    mc.setLanguage(content.getAttributeValue("lang"));
                    mc.setMetadata(parseMetadata(content));
                    try{
                        mc.setSamplingrate((content.getAttributeValue("samplingrate") == null)
                        ? null : NumberParser.parseFloat(content.getAttributeValue("samplingrate")));
                    } catch (Exception ex) {
                        LOG.log(Level.WARNING, "Exception parsing content tag.", ex);
                    }
                    
                    mc.setType(content.getAttributeValue("type"));
                    try{
                        mc.setWidth((content.getAttributeValue("width") == null) ? null
                                : NumberParser.parseInt(content.getAttributeValue("width")));
                    } catch (Exception ex) {
                        LOG.log(Level.WARNING, "Exception parsing content tag.", ex);
                    }
                    
                    mc.setDefaultContent((content.getAttributeValue("isDefault") == null)
                    ? false
                            : Boolean.getBoolean(content.getAttributeValue(
                            "isDefault")));                	
                } else {
                	LOG.log(Level.WARNING, "Could not find MediaContent.");
                }
                

                
            }
        } catch (Exception ex) {
            LOG.log(Level.WARNING, "Exception parsing content tag.", ex);
        }
        
        return (MediaContent[]) values.toArray(new MediaContent[values.size()]);
    }
    
    private MediaGroup[] parseGroup(Element e) {
        List groups = e.getChildren("group", getNS());
        ArrayList values = new ArrayList();
        
        for (int i = 0; (groups != null) && (i < groups.size()); i++) {
            Element group = (Element) groups.get(i);
            MediaGroup g = new MediaGroup(parseContent(group));
            
            for (int j = 0; j < g.getContents().length; j++) {
                if (g.getContents()[j].isDefaultContent()) {
                    g.setDefaultContentIndex(new Integer(j));
                    
                    break;
                }
            }
            
            g.setMetadata(parseMetadata(group));
            values.add(g);
        }
        
        return (MediaGroup[]) values.toArray(new MediaGroup[values.size()]);
    }
    
    private Metadata parseMetadata(Element e) {
        Metadata md = new Metadata();
        // categories
        {
            List categories = e.getChildren("category", getNS());
            ArrayList values = new ArrayList();
            
            for (int i = 0; (categories != null) && (i < categories.size());
            i++) {
                try {
                    Element cat = (Element) categories.get(i);
                    values.add(new Category(cat.getAttributeValue("scheme"),
                            cat.getAttributeValue("label"), cat.getText()));
                } catch (Exception ex) {
                    LOG.log(Level.WARNING, "Exception parsing category tag.", ex);
                }
            }
            
            md.setCategories((Category[]) values.toArray(
                    new Category[values.size()]));
        }
        
        // copyright
        try {
            Element copy = e.getChild("copyright", getNS());
            
            if (copy != null) {
                md.setCopyright(copy.getText());
                md.setCopyrightUrl((copy.getAttributeValue("url") != null)
                ? new URI(copy.getAttributeValue("url")) : null);
            }
        } catch (Exception ex) {
            LOG.log(Level.WARNING, "Exception parsing copyright tag.", ex);
        }
        // credits
        {
            List credits = e.getChildren("credit", getNS());
            ArrayList values = new ArrayList();
            
            for (int i = 0; (credits != null) && (i < credits.size()); i++) {
                try {
                    Element cred = (Element) credits.get(i);
                    values.add(new Credit(cred.getAttributeValue("scheme"),
                            cred.getAttributeValue("role"), cred.getText()));
                    md.setCredits( (Credit[]) values.toArray( new Credit[ values.size()]));
                } catch (Exception ex) {
                    LOG.log(Level.WARNING, "Exception parsing credit tag.", ex);
                }
            }
        }
        
        // description
        try {
            Element description = e.getChild("description", getNS());
            
            if (description != null) {
                md.setDescription(description.getText());
                md.setDescriptionType(description.getAttributeValue("type"));
            }
        } catch (Exception ex) {
            LOG.log(Level.WARNING, "Exception parsing description tag.", ex);
        }
        
        // hash
        try {
            Element hash = e.getChild("hash", getNS());
            
            if (hash != null) {
                md.setHash(new Hash(hash.getAttributeValue("algo"),
                        hash.getText()));
            }
        } catch (Exception ex) {
            LOG.log(Level.WARNING, "Exception parsing hash tag.", ex);
        }
        // keywords
        {
            Element keywords = e.getChild("keywords", getNS());
            
            if (keywords != null) {
                StringTokenizer tok = new StringTokenizer(keywords.getText(),
                        ",");
                String[] value = new String[tok.countTokens()];
                
                for (int i = 0; tok.hasMoreTokens(); i++) {
                    value[i] = tok.nextToken().trim();
                }
                
                md.setKeywords(value);
            }
        }
        // ratings
        {
            List ratings = e.getChildren("rating", getNS());
            ArrayList values = new ArrayList();
            
            for (int i = 0; (ratings != null) && (i < ratings.size()); i++) {
                try {
                    Element rat = (Element) ratings.get(i);
                    values.add(new Rating(rat.getAttributeValue("scheme"),
                            rat.getText()));
                } catch (Exception ex) {
                    LOG.log(Level.WARNING, "Exception parsing rating tag.", ex);
                }
            }
            
            md.setRatings((Rating[]) values.toArray(new Rating[values.size()]));
        }
        // text
        {
            List texts = e.getChildren("text", getNS());
            ArrayList values = new ArrayList();
            
            for (int i = 0; (texts != null) && (i < texts.size()); i++) {
                try {
                    Element text = (Element) texts.get(i);
                    Time start = (text.getAttributeValue("start") == null)
                    ? null : new Time(text.getAttributeValue("start"));
                    Time end = (text.getAttributeValue("end") == null) ? null
                            : new Time(text.getAttributeValue(
                            "end"));
                    values.add(new Text(text.getAttributeValue("type"),
                            text.getTextTrim(), start, end));
                } catch (Exception ex) {
                    LOG.log(Level.WARNING, "Exception parsing text tag.", ex);
                }
            }
            
            md.setText((Text[]) values.toArray(new Text[values.size()]));
        }
        // thumbnails
        {
            List thumbnails = e.getChildren("thumbnail", getNS());
            ArrayList values = new ArrayList();
            
            for (int i = 0; (thumbnails != null) && (i < thumbnails.size());
            i++) {
                try {
                    Element thumb = (Element) thumbnails.get(i);
                    Time t = (thumb.getAttributeValue("time") == null) ? null
                            : new Time(thumb.getAttributeValue(
                            "time"));
                    Integer width = (thumb.getAttributeValue("width") == null)
                    ? null : new Integer(thumb.getAttributeValue("width"));
                    Integer height = (thumb.getAttributeValue("height") == null)
                    ? null : new Integer(thumb.getAttributeValue("height"));
                    values.add(new Thumbnail(
                            new URI(thumb.getAttributeValue("url")), width,
                            height, t));
                } catch (Exception ex) {
                    LOG.log(Level.WARNING, "Exception parsing thumbnail tag.",
                            ex);
                }
            }
            
            md.setThumbnail((Thumbnail[]) values.toArray(
                    new Thumbnail[values.size()]));
        }
        // title
        {
            Element title = e.getChild("title", getNS());
            
            if (title != null) {
                md.setTitle(title.getText());
                md.setTitleType(title.getAttributeValue("type"));
            }
        }
        // restrictions
        {
            List restrictions = e.getChildren("restriction", getNS());
            ArrayList values = new ArrayList();
            
            for (int i = 0; i < restrictions.size(); i++) {
                Element r = (Element) restrictions.get(i);
                Restriction.Type type = null;
                
                if (r.getAttributeValue("type").equalsIgnoreCase("uri")) {
                    type = Restriction.Type.URI;
                } else if (r.getAttributeValue("type").equalsIgnoreCase("country")) {
                    type = Restriction.Type.COUNTRY;
                }
                
                Restriction.Relationship relationship = null;
                
                if (r.getAttributeValue("relationship").equalsIgnoreCase("allow")) {
                    relationship = Restriction.Relationship.ALLOW;
                } else if (r.getAttributeValue("relationship").equalsIgnoreCase("deny")) {
                    relationship = Restriction.Relationship.DENY;
                }
                
                Restriction value = new Restriction(relationship, type,
                        r.getTextTrim());
                values.add(value);
            }
            
            md.setRestrictions((Restriction[]) values.toArray(
                    new Restriction[values.size()]));
        }
        // handle adult
        {
            Element adult = e.getChild("adult", getNS());
            
            if ((adult != null) && (md.getRatings().length == 0)) {
                Rating[] r = new Rating[1];
                
                if (adult.getTextTrim().equals("true")) {
                    r[0] = new Rating("urn:simple", "adult");
                } else {
                    r[0] = new Rating("urn:simple", "nonadult");
                }
                
                md.setRatings(r);
            }
        }
        
        return md;
    }
    
    private PlayerReference parsePlayer(Element e) {
        Element player = e.getChild("player", getNS());
        PlayerReference p = null;
        
        if (player != null) {
            Integer width = (player.getAttributeValue("width") == null) ? null
                    : new Integer(player.getAttributeValue(
                    "width"));
            Integer height = (player.getAttributeValue("height") == null)
            ? null : new Integer(player.getAttributeValue("height"));
            
            try {
                p = new PlayerReference(new URI(player.getAttributeValue("url")),
                        width, height);
            } catch (Exception ex) {
                LOG.log(Level.WARNING, "Exception parsing player tag.", ex);
            }
        }
        
        return p;
    }
    
    public Namespace getNS() {
        return NS;
    }
}
