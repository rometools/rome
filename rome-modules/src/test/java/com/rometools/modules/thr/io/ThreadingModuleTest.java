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
 */
package com.rometools.modules.thr.io;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.rometools.modules.AbstractTestCase;
import com.rometools.modules.thr.ThreadingModule;
import com.rometools.modules.thr.ThreadingModuleImpl;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndEntryImpl;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.SyndFeedOutput;

public class ThreadingModuleTest extends AbstractTestCase {

    public ThreadingModuleTest(String testName) {
        super(testName);
    }

    public void testReadMainSpec() throws IOException, FeedException {
        final SyndFeed feed = getSyndFeed("thr/threading-main.xml");
        List<SyndEntry> entries = feed.getEntries();
        SyndEntry parentEntry = entries.get(0);
        assertEquals("should be the parent entry", "My original entry", parentEntry.getTitle());
        assertNull(parentEntry.getModule(ThreadingModule.URI));

        SyndEntry replyEntry = entries.get(1);
        assertEquals("should be the reply entry", "A response to the original", replyEntry.getTitle());
        Module module = replyEntry.getModule(ThreadingModule.URI);
        assertNotNull(module);
        ThreadingModule threadingModule = (ThreadingModule) module;
        assertEquals("tag:example.org,2005:1", threadingModule.getRef());
        assertEquals("application/xhtml+xml", threadingModule.getType());
        assertEquals("http://www.example.org/entries/1", threadingModule.getHref());
        assertNull(threadingModule.getSource());
    }

    public void testGenerate() throws IOException, FeedException {
        final SyndFeed feed = getSyndFeed("thr/threading-main.xml");
        List<SyndEntry> entries = feed.getEntries();

        // create a new "root" entry that the next entry will reference to
        SyndEntry newRootEntry = new SyndEntryImpl();
        newRootEntry.setTitle("New, 2nd root entry");
        newRootEntry.setUri("tag:example.org,2005:2");
        newRootEntry.setLink("http://www.example.org/entries/2");
        entries.add(newRootEntry);

        // create a new reply entry that will reference the new root entry
        SyndEntry newReplyEntry = new SyndEntryImpl();
        newReplyEntry.setTitle("New test reply entry");
        newReplyEntry.setUri("tag:example.org,2005:2,1");

        ThreadingModule threadingModule = new ThreadingModuleImpl();
        threadingModule.setRef("tag:example.org,2005:2");
        threadingModule.setType("application/xhtml+xml");
        threadingModule.setHref("http://www.example.org/entries/2");
        threadingModule.setSource("http://example.org/entries/2");

        newReplyEntry.getModules().add(threadingModule);
        entries.add(newReplyEntry);

        File outputFile = new File("target/threading-testGenerate.xml");
        final SyndFeedOutput output = new SyndFeedOutput();
        output.output(feed, outputFile);

        // read back in and validate
        final SyndFeed generatedFeed = getSyndFeed(outputFile);
        SyndEntry generatedReplyEntry = generatedFeed.getEntries().get(3);
        assertNotNull(generatedReplyEntry);
        ThreadingModule generatedReplyThreadingModule = (ThreadingModule) generatedReplyEntry.getModule(ThreadingModule.URI);
        assertEquals("tag:example.org,2005:2", generatedReplyThreadingModule.getRef());
        assertEquals("application/xhtml+xml", generatedReplyThreadingModule.getType());
        assertEquals("http://www.example.org/entries/2", generatedReplyThreadingModule.getHref());
        assertEquals("http://example.org/entries/2", generatedReplyThreadingModule.getSource());
    }

    public void testEnd2End() throws IOException, FeedException {
        final SyndFeed feed = getSyndFeed("thr/threading-main.xml");
        final SyndFeedOutput output = new SyndFeedOutput();
        File outputFile = new File("target/threading-main-generated.xml");
        output.output(feed, outputFile);

        final SyndFeed feedOut = getSyndFeed(outputFile);

        ThreadingModule moduleSrc = (ThreadingModule) feed.getEntries().get(1).getModule(ThreadingModule.URI);
        ThreadingModule moduleOut = (ThreadingModule) feedOut.getEntries().get(1).getModule(ThreadingModule.URI);
        assertEquals(moduleSrc, moduleOut);
        assertEquals("tag:example.org,2005:1", moduleSrc.getRef());
        assertEquals("tag:example.org,2005:1", moduleOut.getRef());
    }

    private SyndFeed getSyndFeed(final File file) throws IOException, FeedException {
        return new SyndFeedInput().build(file);
    }

    private SyndFeed getSyndFeed(final String filePath) throws IOException, FeedException {
        final String fullPath = getTestFile(filePath);
        final File file = new File(fullPath);
        return new SyndFeedInput().build(file);
    }

}
