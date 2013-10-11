/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rometools.certiorem.hub;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.rometools.fetcher.impl.SyndFeedInfo;

/**
 * Extends SyndFeedInfo to also track etags for individual entries.
 * This may be used with DeltaFeedInfoCache to only return feed with a subset of entries that have changed since last fetch.
 * 
 * @author najmi
 */
public class DeltaSyndFeedInfo extends SyndFeedInfo {    
    Map<String, String> entryTagsMap = new HashMap<String, String>();
    Map<String, Boolean> changedMap = new HashMap<String, Boolean>();
    
    private DeltaSyndFeedInfo() {        
    }
    
    public DeltaSyndFeedInfo(SyndFeedInfo backingFeedInfo) {
        this.setETag(backingFeedInfo.getETag());
        this.setId(backingFeedInfo.getId());
        this.setLastModified(backingFeedInfo.getLastModified());
        this.setSyndFeed(backingFeedInfo.getSyndFeed());
    }
    
    /**
     * Gets a filtered version of the SyndFeed that only has entries that were changed in the last setSyndFeed() call.
     * 
     * @return 
     */
    @Override
    public synchronized SyndFeed getSyndFeed() {
        try {
            SyndFeed feed = (SyndFeed) super.getSyndFeed().clone();
            
            List<SyndEntry> changedEntries = new ArrayList<SyndEntry>();
            
            List<SyndEntry> entries = feed.getEntries();
            for (SyndEntry entry : entries) {
                if (changedMap.containsKey(entry.getUri())) {
                    changedEntries.add(entry);                    
                }
            }
            
            feed.setEntries(changedEntries);
            
            return feed;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Overrides super class method to update changedMap and entryTagsMap for tracking changed entries.
     * 
     * @param feed 
     */
    @Override
    public final synchronized void setSyndFeed(SyndFeed feed) {
        super.setSyndFeed(feed);
        
        changedMap.clear();
        List<SyndEntry> entries = feed.getEntries();
        for (SyndEntry entry : entries) {
            String currentEntryTag = computeEntryTag(entry);
            String previousEntryTag = entryTagsMap.get(entry.getUri());

            if ((previousEntryTag == null) || (!(currentEntryTag.equals(previousEntryTag)))) {
                //Entry has changed
                changedMap.put(entry.getUri(), Boolean.TRUE);
            }
            entryTagsMap.put(entry.getUri(), currentEntryTag);
        }
    }
    
    private String computeEntryTag(SyndEntry entry) {

        //Following hash algorithm suggested by Robert Cooper needs to be evaluated in future.
//    int hash = ( entry.getUri() != null ? entry.getUri().hashCode() : entry.getLink().hashCode() ) ^ 
//            (entry.getUpdatedDate() != null ? entry.getUpdatedDate().hashCode() : entry.getPublishedDate().hashCode()) ^ 
//            entry.getTitle().hashCode() ^ 
//            entry.getDescription().hashCode();

        
        String id = entry.getUri();
        Date updateDate = entry.getUpdatedDate();
        Date publishedDate = entry.getPublishedDate();
        if (updateDate == null) {
            if (publishedDate != null) {
                updateDate = publishedDate;
            } else {                       
                //For misbehaving feeds that do not set updateDate or publishedDate we use current tiem which pretty mucg assures that it will be viewed as changed even when it is not
                updateDate = new Date();
            }
        }
        String key = id + ":" + entry.getUpdatedDate();
        return computeDigest(key);        
    }
    
    private String computeDigest(String content) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            byte[] digest = md.digest(content.getBytes());
            BigInteger bi = new BigInteger(digest);
            return bi.toString(16);
        } catch (Exception e) {
            return "";
        }
    }        
}
