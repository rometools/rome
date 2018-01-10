/*
 * Copyright 2018 Tom G., tomgapplicationsdevelopment@gmail.com
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

package com.rometools.modules.wfw;

import com.rometools.modules.AbstractTestCase;
import com.rometools.modules.wfw.io.WfwModuleGenerator;
import com.rometools.modules.wfw.WfwModule;
import com.rometools.modules.wfw.WfwModuleImpl;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.io.SyndFeedInput;
import junit.framework.Test;
import junit.framework.TestSuite;

import java.io.File;

/**
 * Test to verify correctness of Wfw Module.
 */
public class WfwTest extends AbstractTestCase {
    private String commentRssSample = "https://kenfm.de/kenfm-spezial-coexist-der-islam/feed/";

    public WfwTest(final String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
    }

    @Override
    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        return new TestSuite(WfwTest.class);
    }

    public void testGetNamespaceUri() {
        assertEquals("Namespace", WfwModule.URI, new WfwModuleGenerator().getNamespaceUri());
    }

    public void test() throws Exception {
	final File testdata = new File(super.getTestFile("wfw/wfw.xml"));
	final SyndEntry entry = new SyndFeedInput().build(testdata).getEntries().get(0);
	final WfwModule wfwModule = (WfwModule) entry.getModule(WfwModule.URI);
	assertEquals(commentRssSample, wfwModule.getCommentRss());
    }
    
    public void testCommentRssShouldWork() {
        final WfwModuleImpl wfwModule = new WfwModuleImpl();
        wfwModule.setCommentRss(commentRssSample);
        assertEquals(commentRssSample, wfwModule.getCommentRss());
    }
}
