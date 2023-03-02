## ROME 1.0 Release (Mar 12, 2009)

### Release Notes

-   ROME can now optionally preserve WireFeed (ie, Atom/RSS specific) data and 
    make it available via the SyndFeed data model.

### Changelog

1.  [Issue 121](http://java.net/jira/browse/ROME-121):
    RSS item category iteration should try to reflect document order
2.  New property preserveWireFeed available on SyndFeedInput\
    WireFeeds will be preserved if the property preserveWireFeed is set
    on the SyndFeedInput object it is built from. Atom/RSS Entry/Item
    objects are also available from SyndEntry objects if the WireFeed is
    preserved using the new getWireEntry() method. See [Preserving
    WireFeeds (rome)](rome-core/getting-started/preserve-wirefeed.html) for details.
    
### Dependencies

-   J2SE 1.4+
-   [JDOM 1.0](http://www.jdom.org/)

### Downloads

-   [rome-1.0.jar](rome-1.0.jar)
-   [rome-1.0-sources.jar](rome-1.0-sources.jar)
-   [rome-1.0-javadoc.jar](rome-1.0-javadoc.jar)
