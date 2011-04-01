package com.sun.syndication.io;

/**
 * Adds the ability to give a direct wire feed reference to plugin
 * modules that need to call back to their wire feed to complete
 * parsing of their particular namespace. Examples of such parsers
 * include the SSE091Parser.
 */
public interface DelegatingModuleParser extends ModuleParser {
    /**
     * Provides a parent wirefeed reference to this ModuleParser,
     * or "plugin-in".
     *
     * @param feedParser the parent wirefeed parser for this plugin.
     */
    void setFeedParser(WireFeedParser feedParser);
}
