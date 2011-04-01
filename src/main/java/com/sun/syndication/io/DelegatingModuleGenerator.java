package com.sun.syndication.io;

/**
 * Adds the ability to give a direct wire feed generator reference to plugin
 * modules that need to call back to their wire feed to complete
 * generation of their particular namespace. Examples of such parsers
 * include the SSE091Generator.
 */
public interface DelegatingModuleGenerator extends ModuleGenerator {
    /**
     * Provides a parent wirefeed reference to this ModuleParser,
     * or "plugin-in".
     *
     * @param feedGenerator the parent wirefeed generator for this plugin.
     */
    void setFeedGenerator(WireFeedGenerator feedGenerator);
}
