/*
 * OpenSearchModuleTest.java
 * JUnit based test
 *
 * Created on April 25, 2006, 8:56 PM
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

package com.rometools.modules.opensearch;

import java.io.File;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.rometools.modules.AbstractTestCase;
import com.rometools.modules.opensearch.OpenSearchModule;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.SyndFeedOutput;

public class OpenSearchModuleTest extends AbstractTestCase {

    public OpenSearchModuleTest(final String testName) {
        super(testName);
    }

    public static Test suite() {
        final TestSuite suite = new TestSuite(OpenSearchModuleTest.class);

        return suite;
    }

    public void testEndToEnd() throws Exception {
        final SyndFeedInput input = new SyndFeedInput();
        final File test = new File(super.getTestFile("os"));
        final File[] files = test.listFiles();
        for (int j = 0; j < files.length; j++) {
            if (!files[j].getName().endsWith(".xml")) {
                continue;
            }
            final SyndFeed feed = input.build(files[j]);
            final Module m = feed.getModule(OpenSearchModule.URI);
            final SyndFeedOutput output = new SyndFeedOutput();
            final File outfile = new File("target/" + files[j].getName());
            output.output(feed, outfile);
            final SyndFeed feed2 = input.build(outfile);
            assertEquals(m, feed2.getModule(OpenSearchModule.URI));
        }
    }

}
