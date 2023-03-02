## ROME 0.4 Beta (Sep 27, 2004)

### Release Notes

-   Changed naming convention of bean interfaces and implementations (i.e.: 
    SyndFeedI/SyndFeed are now SyndFeed/SyndFeedImpl)
-   New XmlReader that handles charset encoding as defined by the XML 1.0 
    specification and RFC 3023
-   Support of RSS 0.91 Netscape and RSS 0.91 Userland as distinct feed types
-   All feed types have Modules support
-   Added checks to generators reducing the chances of generating invalid feeds
-   Comprehensive Unit testing
-   Bug fixes
-   More documentation and samples
-   Dependencies, upgraded to JDom 1.0 and removed Xerces

### Changelog

1.  Fix. Date elements on generated feeds use the right format now\
    There were some Date elements in Atom 0.3, DCModule, SyModule, RSS
    0.91 and RSS 0.93 that were incorrectly formating the date \[they
    were just doing a toString() \]. Now they use the RFC822 and W3C
    format as indicated in their specs.
2.  Fix. SyndFeed and SyndEntry getModule(DCModule.URI) method always
    returns a DCModule, never null\
    This issue is related to Fix #19 in v0.3. The DCModule is
    \'special\' for Synd\*, it must always be there. If it is not, it is
    created implicitly when needed. This was not happening when asking
    explicitly for the DCModule through the Synd\* interfaces.
3.  Addition. Added ParseFeedException to the \*Input classes\
    This new exception report the line and column number in the XML
    document where the parsing has failed.
4.  Change. Renamed \'\*I\' interfaces to just \'\*\' and default
    implementations to \'\*Impl\'\
    The Synd\* and Module interfaces/classes were affected. For example
    the interface that used to be SyndFeedI is now SyndFeed and the
    class that used to be SyndFeed is now SyndFeedImpl.
5.  Change. Ant \'build.xml\' files have been improved\
    The build.xml were re-written instead just using the Maven generated
    ones.
6.  Fix. DateParser now uses lenient parsing so as to work with JDK 1.5\
    DateParser currently setLenient to false. This does not work with
    JDK 1.5. Changing the DateParser to setLient to true.
7.  Change. SyndCategoryListFacade is not a public class anymore\
    It\'s now a package private class (it should have been like that in
    the first place).
8.  Addition. Added a protected constructor to the Synd\*Impl classes\
    This constructor takes a Class parameter. It allows implementations
    extending SyndContentImpl to be able to use the ObjectBean
    functionality with extended interfaces (additional public
    properties). Use case: Hibernate beans that have an \'Id\' Long
    property, a new interface HSynd\* (extending Synd\*) and a new
    implementation HSynd\*Impl (extending Synd\*Impl) where the clone(),
    equals(), hashCode() and toString() methods take the properties of
    the extension into account.
9.  Project layout change. Moved samples project to subprojects dir\
    The \'samples\' project was moved from \'rome/modules/samples\' to
    \'rome/subprojects/samples\'. The \'rome/modules\' project is left
    for Module subprojects only.
10. Fix. Plugin manager bug didn\'t allow custom plugins to replace core
    plugins\
    All plugins are in a Map keyed off by its type (parsers, generators,
    converters) or URI (modules). There is also a helper List containing
    the plugins, this list is scanned when looking for a plugin (for
    example when selecting the right parser). Plugins were added to the
    List without checking if another element in the List was using the
    same key. Now the List is built using the Map values, thus the
    overwriting works fine.
11. Fix. RSS 2.0 Converter (Wire -\> Synd - Wire) was not processing
    modules\
    The RSS 2.0 Converter now processes feed and entry modules both
    ways.
12. Fix. New properties introspection mechanism in common classes\
    The java.beans.Introspector does not work as expected on interface
    properties (it doesn\'t scan properties of the super interfaces).
    Now, a private implementation of a properties introspector is used
    by the common bean classes.
13. Change. Refactored private CopyFrom helper class\
    It was com.sun.syndication.feed.synd.impl.SyndCopyFrom now it is
    com.sun.syndication.common.impl.CopyFromHelper.
14. Fix. RSS2.0 Wire-Synd converter handles propertly RSS and DC
    categories\
    If the RSS2.0 feed had DC Module categories (subjects) this would
    override the RSS native categories. Now they are aggregated.
15. Change/Fix. CloneableBean can take an ignore-properties set\
    This change is useful for bean that have convenience properties
    mapping to other properties. SyndFeed and SyndEntry that map some of
    its properties (ie publishedDate, author, categories) to DC Module
    properties. There is not point cloning the convenience ones as they
    are just facades.\
    This fixes a SyndFeedImpl cloning problem with the categories. There
    is a package private list for the categories to DC subject mapping.
    The problem was related to accesibility of this package private list
    implementation by the CloneableBean. The change enables the
    blacklisting of certain properties (in this categories) when
    cloning.
16. Change. Refactored Parsers/Generator classes\
    Introduced a base parser and base generator to handle modules. For
    the feed types that define modules support, the modules have to be
    defined the rome.properties file. For RSS 1.0 and Atom 0.3 Dublin
    Core and Syndication modules are defined, the first one at feed and
    entry level the second one at feed level. Note this was already done
    but hardwired in the specific feed type parsers and generators, now
    it is done in the base parser and base generator. Some code clean up
    and removal of duplicated code was also done.
17. Change. Defined and implemented precedence order for native and
    module elements in feeds\
    For feeds supporting modules, some of the module defines elements
    collide with the feed native elements (this depends on the feed
    type, and it may affect the data such as publish-date, author,
    copyright, categories). The SyndFeed and SyndEntry properties
    documented as convenience properties are (can be) affected. The
    convenience properties map into DC Module properties.\
    Rome now defines precedence of feed native elements over module
    elements when converting from WireFeed to SyndFeed. This is, in the
    situation of a clash, the native element data prevails and the the
    module element data is lost.\
    When converting from SyndFeed to WireFeed, if a SyndFeed convenience
    property has a native mapping in the target feed type it will be in
    both the native element and the DC Module element if the feed type
    defines support for the DC module. The data will appear twice in the
    feed, in the native elements and in the DC module elements.
18. Change. Module namespaces are defined at root element\
    Module namespaces are always defined in the root element. The
    ModuleGenerator interface has a new method that returns all the
    namespaces used by the module, the generators use the namespaces
    returned by this method to inject them in the feed root element.
19. Change/Fix. Test cases refactoring\
    Some refactoring in the test cases for the Synd\* entities. Some
    minor bugs (typos in constants) were found and fixed in the
    parsers.\
    The Ops and Synd tests are 100% complete. Not that they test 100% of
    Rome functionality but 100% of what they suppose to test.
20. Fix. Atom 0.3 content elements with XML mode were not being parsed
    and converted properly\
    When mode is XML, free text was not being processed, only
    sub-elements were being processed.\
    The Parser when parsing content with XML mode skips Atom namespace
    in the output of the processed fragment.\
    The Converter when going down pushes content data using XML mode,
    which is Atom\'s default (it was ESCAPED before).
21. Addition. Constraints in data length and number of items are
    observed by RSS generators\
    As part of the generators refactoring the generators now verify the
    data in the Rome beans does not generate invalid feeds.
22. Addition. New XmlReader that detects charset encoding\
    The XmlReader class handles the charset encoding of XML documents in
    Files, raw streams and HTTP streams. It following the rules defined
    by HTTP, MIME types and XML specifications. All this is nicely
    explained by Mark Pilgrim in his blog,
    [http://diveintomark.org/archives/2004/02/13/xml-media-types
    (Archived)](https://web.archive.org/web/20060706153721/http://diveintomark.org/archives/2004/02/13/xml-media-types)
23. Rome now uses JDOM 1.0\
    Dependencies have been updated to use JDOM 1.0. No code changes were
    needed because of this.
24. Change. The length property in the RSS Enclosure bean is now a long\
    It used to be an int, but as it is meant to specify arbitrary
    lengths (in could be in the order of several megabytes) it was
    changed to be a long.
25. Addition. SyndFeed and SyndEntry beans have a \'uri\' property\
    It is used with RSS0.94 & RSS2.0 guid elements and with Atom0.3 id
    elements.
26. Addition. New sample, FeedServlet\
    Added a new sample, FeedServlet. This servlet creates a feed and
    returns a feed.
27. Change. RSS0.91 Userland and RSS0.91 Netscape are handled as
    different feed types\
    Instead having a single set of parser/converter/generator
    implementations there is one set for each one of them. This allows
    to differenciate incoming RSS0.91 Userland feeds from RSS0.91
    Netscape feeds as well as choosing which one to generate.

### Known Issues

None.

### Dependencies

-   J2SE 1.4+
-   [JDOM 1.0](http://www.jdom.org/)

### Downloads

-   [rome-0.4.zip](rome-0.4.zip)
-   [rome-0.4.tar.gz](rome-0.4.tar.gz)
-   [rome-0.4-src.zip](rome-0.4-src.zip)
-   [rome-0.4-src.tar.gz](rome-0.4-src.tar.gz)
-   [rome-samples-0.4-src.tar.gz](rome-samples-0.4-src.tar.gz)
-   [rome-samples-0.4-src.zip](rome-samples-0.4-src.zip)

