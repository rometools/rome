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
package com.rometools.rome.io;

/**
 * Adds the ability to give a direct wire feed reference to plugin modules that need to call back to
 * their wire feed to complete parsing of their particular namespace. Examples of such parsers
 * include the SSE091Parser.
 */
public interface DelegatingModuleParser extends ModuleParser {
    /**
     * Provides a parent wirefeed reference to this ModuleParser, or "plugin-in".
     *
     * @param feedParser the parent wirefeed parser for this plugin.
     */
    void setFeedParser(WireFeedParser feedParser);
}
