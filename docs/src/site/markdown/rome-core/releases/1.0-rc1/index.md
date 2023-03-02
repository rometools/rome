## ROME 1.0 RC1 (Jul 16, 2007)

### Release Notes

-   Several XmlReader fixes
-   Several Atom 1.0 bean, parser and generator fixes
-   Some RSS fixes
-   Removal of unused namespaces

### Changelog

1.  New. XmlReader support for default encoding\
    The XmlReader can be set with an alternate default encoding in case
    no encoding has been detected from the transport (HTTP), the stream
    or the XML prolog. if no value is set the default fallback rules
    based on the content-type will be used. The alternate default
    encoding can be set/viewed via a static methods,
    **setDefaultEncoding()** and **getDefaultEncoding()**.
2.  Fix. Atom 1.0 links were generated without title and length
    attributes.\
    The Atom 1.0 Generator was not generating title and length
    attributes when values are present.
3.  Fix. XmlReader, multi-line prolog encoding detection.\
    XmlReader handles properly xml-prolog detection when prolog goes
    over multiple lies (such as G groups feeds).
4.  Fix. Base64 decoding was failing under certain padding conditions.
5.  Fix. XmlReader fixes\
    Fixed bug that if BOM is UTF8 was not being set to UTF8. Changed
    logic to use Buffered stream instead pushback stream for all
    encoding detection. Changed logic of xml prolog detection to avoid
    having a buffer with half of a unicode character (instead filling up
    the buffer looking up to first \'\>\' which means it a valid
    buffer).
6.  New. XmlReader supports default encoding at instance level.\
    Via a new constructor is possible to indicate a default encoding
    different than the default encoding at class level.
7.  Fix. Making the EqualsBean to follow equals contract.\
    For X.equals(null) it was throwing a NullPointerException, now it
    returns FALSE.
8.  Fix. Render Atom icon and logo attributes.\
    AtomGenerator now adds icon and logo elements to xml tree
9.  Fix. Updated AtomPub namespace to its permenent home.\
    AtomService namespace updated to
    [http://www.w3.org/2007/app](http://www.w3.org/2007/app)
10. New. Added support for configuration per classloader level.\
    The PluginManager (handles Parsers and Generators) now is singleton
    at classloader level allowing different configurations in different
    classloaders.
11. Atom parser: better relative URI handling\
    Instead of simply resolving each relative URI at runtime and saving
    only the resolved one, we now save both the relative URI and the
    resolve one. We introduced the following new methods to provide
    access to the resolved URI.
    -   Link.getLinkResolved()
    -   Link.setLinkResolved()
    -   Category.getSchemeResolved()
    -   Category.setSchemeResolved()
    -   Person.getUriResolved()
    -   Person.setUriResolved()
12. Utility methods useful in working with Atom protocol feeds\
    Added a couple of methods to make it easier to deal with Atompub
    feeds.
    -   Entry.isMediaEntry()
    -   Atom10Parser.parseEntry()
    -   Atom10Generator.serializeEntry()
13. Bugs fixed\
    Fixed the following bugs:
    -   49 Better content/summary mapping
    -   53 Content.setType not working with subtitles atom 1.0
    -   56 fix of bug #39 leads to invalid atom feeds
    -   63 Missing link attribute when generating Atom 1.0
    -   64 ROME\'s Atom parser doesn\'t pick up multiple alt links
    -   65 Atom feeds not including logo image
    -   71 encoding problem in XmlReader.getXmlProlog()
    -   79 Feed.setIcon()/setLogo() ignored by Atom10Generator
    -   81 SyndFeedImpl.equals() does not obey equals contract
14. Fix. Parsers where ignoring namespaced prefixed Attributes.\
    If an XML feed uses a prefix for the Atom elements and the
    attributes of Atom elements use the prefix the parser was not
    picking up those attributes.\
    The fix makes the parser to look for prefixed and non-prefixed
    attributes.
15. Fix. Atom Feed and Entry beans author and category property getters\
    They were returning NULL when there were not authors or categories,
    they must return an empty list.
16. New. Switch to enable/disable relative URI resolution in Atom 1.0
    Parser.\
    The Atom10Parser class has a static method, setResolveURIs(boolean)
    that enables/disables relative URI resolution.
17. New. XmlReader handling content-type charset values has been
    relaxed.\
    XmlReader handles content-type charset encoding value within single
    quotes and double quotes.
18. Fix. Links, authors and contributors properties in SyndFeed were not
    implementing the semantics of list properties.\
    They were returning NULL instead, now they return an empty list if
    not values are set.
19. Fix. RSS conversion of a SyndFeed was losing the link of the feed if
    the links property was used instead the link property.\
    Over time the SyndFeed has been modified to support more Atom
    specific properties and their cardinality, conversion to RSS of
    these properties was not always taken care.\
    The RSS converter has been changed so the link from SyndFeed is
    taken as channel link and if not set the first value of the links
    property is taken.
20. Fix. WireFeedInput throws IllegalArgumentException if the feed type
    is not recognized.\
    Previously the IllegalArgumentException was wrapped by a
    ParsingFeedException (Reported by [Issue
    91](http://java.net/jira/browse/ROME-91)).
21. Fix. SyndFeedImpl.equals(other) checks for instance of other before
    casting.\
    The underlying ObjectBean does this check, but in this method a cast
    is done before to obtain the foreign markup, no the instance check
    is peformed before to avoid a class cast exception.
22. Fix. Atom content based elements related fixes
    -   Atom 0.3 Parser/Generator
        -   Changed title to be treated as a Content construct.
            ([http://www.mnot.net/drafts/draft-nottingham-atom-format-02.html#rfc.section.4.3](http://www.mnot.net/drafts/draft-nottingham-atom-format-02.html#rfc.section.4.3))
    -   Atom 1.0 Generator:
        -   changed feed title/subtitle and entry title to be treated as
            Content constructs. (Parser had this implemented already.)
        -   added title attribute to links. (Parser had this implemented
            already.)
        -   fixed content parsing for some XML content types. e.g.
            (application/xhtml+xml)
23. Fix. Atom link and enclosures handling
    -   Atom 0.3 Converter
        -   fixed link parsing code to parse all links (not just the
            first alternate link) and added enclosure support via link
            rel=\"enclosure\".
        -   changed title conversion to use Content instead of plain
            text.
    -   Atom 1.0 Converter
        -   added SyndEnclosure to atom:link rel=enclosure conversion.
24. Fix. RSS 1.0 URI generation
    -   RSS 1.0 Generator
        -   channel/items/Seq/li/@resource now get\'s the item URI
            instead of the Link.
            ([http://web.resource.org/rss/1.0/spec#s5.3.5](http://web.resource.org/rss/1.0/spec#s5.3.5))
25. Fix. Javadocs corrections.
    -   Fixed some javadoc comments for SyndEntry.
26. Fix. Atom content based elements were not parsed with XML mime
    types.\
    If the mime type was and XML mime type the content value was being
    lost on parsing.
27. Fix. duplication of content:encoded elements when reading/writing
    and RSS feed.\
    content:encoded elements are treated special, without a module, they
    have to be removed from the foreign markup to avoid duplication in
    case of read/write. Note that this fix will break if a content
    module is used.
28. New. XmlFixerReader converts \'&\' into \'&\' when there is no
    matching entity.\
    Feeds commonly use \'&\' instead \'&\' in their content, this change
    converts those orphant \'&\'s into \'&\'s.
29. Fix. RSS090Parser does not set the URI property.\
    The fix honors the documentation \"For RSS 0.91, RSS 0.92, RSS 0.93
    & RSS 1.0 \... the SyndEntry uri property will be set with the value
    of the link element\...\"
30. New. Removal of all unused namespaces from generated feeds.\
    The generators now remove all unused namespaces from the XML
    document before generating it.

### Known Issues

None.

### Dependencies

-   J2SE 1.4+
-   [JDOM 1.0](http://www.jdom.org/)

### Downloads

-   [rome-1.0RC1.zip](rome-1.0RC1.zip)
-   [rome-1.0RC1.tar.gz](rome-1.0RC1.tar.gz)
-   [rome-1.0RC1-src.zip](rome-1.0RC1-src.zip)
-   [rome-1.0RC1-src.tar.gz](rome-1.0RC1-src.tar.gz)
-   [rome-samples-1.0RC1-src.zip](rome-samples-1.0RC1-src.zip)
-   [rome-samples-1.0RC1-src.tar.gz](rome-samples-1.0RC1-src.tar.gz)
