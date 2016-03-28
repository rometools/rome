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
package com.rometools.rome.unittest;

/**
 *
 * <p>
 *
 * @author Alejandro Abdelnur
 *
 */
public class TestOpsRSS091N extends FeedOpsTest {

    public static void main(final String[] args) throws Exception {
        final FeedOpsTest test = new TestOpsRSS091N();
        test.testWireFeedSyndFeedConversion();
    }

    public TestOpsRSS091N() {
        super("rss_0.91N", true);
    }

}
