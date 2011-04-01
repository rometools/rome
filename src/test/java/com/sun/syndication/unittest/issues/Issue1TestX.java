/*
 *  Copyright 2011 robert.cooper.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */

package com.sun.syndication.unittest.issues;

import com.sun.syndication.unittest.SyndFeedTest;

/**
 *
 * @author robert.cooper
 */
public class Issue1TestX extends SyndFeedTest {

    public Issue1TestX(){
        super("rss_2.0", "jira_issue1.xml");
    }

}
