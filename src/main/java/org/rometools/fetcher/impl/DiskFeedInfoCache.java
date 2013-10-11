/*
 * Copyright 2005 Sun Microsystems, Inc.
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
package org.rometools.fetcher.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import javax.swing.text.Utilities;


/**
 * Disk based cache.
 */
public class DiskFeedInfoCache implements FeedFetcherCache {
    
    protected String cachePath = null;
    public DiskFeedInfoCache(String cachePath) {
        this.cachePath = cachePath;
    }
    public SyndFeedInfo getFeedInfo(URL url) {
        SyndFeedInfo info = null;
        String fileName = cachePath + File.separator + "feed_"
                + replaceNonAlphanumeric(url.toString(),'_').trim();
        FileInputStream fis;
        try {
            fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            info = (SyndFeedInfo)ois.readObject();
            fis.close();
        } catch (FileNotFoundException fnfe) {
            // That's OK, we'l return null
        } catch (ClassNotFoundException cnfe) {
            // Error writing to cache is fatal
            throw new RuntimeException("Attempting to read from cache", cnfe);
        } catch (IOException fnfe) {
            // Error writing to cache is fatal
            throw new RuntimeException("Attempting to read from cache", fnfe);
        }
        return info;
    }
    
    public void setFeedInfo(URL url, SyndFeedInfo feedInfo) {
        String fileName = cachePath + File.separator + "feed_"
                + replaceNonAlphanumeric(url.toString(),'_').trim();
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(feedInfo);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            // Error writing to cache is fatal
            throw new RuntimeException("Attempting to write to cache", e);
        }
    }
    
    public static String replaceNonAlphanumeric(String str, char subst) {
        StringBuffer ret = new StringBuffer(str.length());
        char[] testChars = str.toCharArray();
        for (int i = 0; i < testChars.length; i++) {
            if (Character.isLetterOrDigit(testChars[i])) {
                ret.append(testChars[i]);
            } else {
                ret.append( subst );
            }
        }
        return ret.toString();
    }
    
    /**
     * Clear the cache. 
     */
    public synchronized void clear() {
    	final File file = new File(this.cachePath);
    	//only do the delete if the directory exists
    	if( file.exists() && file.canWrite() ) {
    		//make the directory empty
    		final String[] files = file.list();
    		final int len = files.length;
    		for( int i=0; i<len; i++ ) {
    			File deleteMe = new File(this.cachePath + File.separator + files[i]);
    			deleteMe.delete();
    		}
    		
    		//don't delete the cache directory
    	}
    }
    
    public SyndFeedInfo remove( URL url ) {
        SyndFeedInfo info = null;
        String fileName = cachePath + File.separator + "feed_"
                + replaceNonAlphanumeric(url.toString(),'_').trim();
        FileInputStream fis;
        try {
            fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            info = (SyndFeedInfo)ois.readObject();
            fis.close();
            
            File file = new File( fileName );
            if( file.exists() ) { 
            	file.delete();
            }
        } catch (FileNotFoundException fnfe) {
            // That's OK, we'l return null
        } catch (ClassNotFoundException cnfe) {
            // Error writing to cahce is fatal
            throw new RuntimeException("Attempting to read from cache", cnfe);
        } catch (IOException fnfe) {
            // Error writing to cahce is fatal
            throw new RuntimeException("Attempting to read from cache", fnfe);
        }
        return info;   	
    }
}
