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
package com.rometools.rome.unittest.issues;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.unittest.FeedTest;

/**
 * Test for #137: RSS item description is not complete.
 */
public class Issue137Test extends FeedTest {

    public Issue137Test() {
        super("issue137-rss.xml");
    }

    public void testDescription() throws Exception {
        final SyndFeed feed = this.getCachedSyndFeed();
        assertEquals("<img src=\"http://www.moe.gov.sg/media/spotlight/assets_c/2009/09/spotlight-single-session-thumb-120x80-282.jpg\""
                + " alt=\"Teacher and his students tossing frisbees into the air\" /><p>MOE will be <a href\n"
                + "=\"http://www.moe.gov.sg/media/press/2009/09/moe-to-build-new-primary-schoo.php\">building 11 new primary\n"
                + " schools and upgrading another 28 existing schools from November 2009</a>. This is the first phase of\n"
                + " MOE's plans to enhance primary school infrastructure to facilitate the transition of all primary schools\n"
                + " to a single-session model to support a higher quality primary education.</p>", feed.getEntries().get(0).getDescription().getValue());
        assertEquals("<img src=\"http://www.moe.gov.sg/media/spotlight/assets_c/2009/09/spotlight-wps-thumb-120x80-280\n"
                + ".jpg\" alt=\"Minister speaking at WPS 2009\" /><p>At the Work Plan Seminar 2009, Minister for Education\n"
                + " Dr Ng Eng Hen <a href=\"http://www.moe.gov.sg/media/speeches/2009/09/17/work-plan-seminar.php\">talked\n"
                + " about the importance of language &#38; communication skills and growing a world-class education service\n"
                + "</a>. He spoke of <a href=\"http://www.moe.gov.sg/media/press/2009/09/teachers-the-heart-of-quality.php\"\n"
                + ">strengthening the teaching track and of a teacher-driven culture of professional excellence</a>.</p\n" + ">", feed.getEntries().get(1)
                .getDescription().getValue());
        assertEquals("<img src=\"http://www.moe.gov.sg/media/spotlight/assets_c/2009/09/spotlight-moa-2009-thumb-120x80-278"
                + ".jpg\" alt=\"Students at a podcast session\" />\n <p>A total of 275 schools will <a href=\"http:/"
                + "/www.moe.gov.sg/media/press/2009/09/recognising-school-achievement.php\">receive 580 Special and Level\n"
                + " Two Awards this year under the MOE Masterplan of Awards</a>. These awards will be presented at the 2009\n"
                + " MOE Work Plan Seminar on 17 Sep 09 by the Minister for Education, Dr Ng Eng Hen.</p>", feed.getEntries().get(2).getDescription().getValue());
    }

}
