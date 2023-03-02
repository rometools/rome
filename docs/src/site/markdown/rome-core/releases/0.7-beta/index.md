## ROME 0.7 Beta (Sep 09, 2005)

### Release Notes

-   We got a cool logo
-   Bug fixes
-   Several Date and time parsing improvements including support for
    custom parsing masks

### Changelog

1.  Fix. RFC-882 dates parsing and generation were using localized names
    for day and month names\
    The date parser and generator were using the JVM default Locale
    instead forcing an English Locale to use day and month names in
    English as specified by RFC-822. Now US Locale is used.
2.  Fix. The \'ttl\' element of RSS0.94 and RSS2.0 feeds was not being
    parsed\
    The parsers now parse the \'ttl\' element and it is available in the
    resulting Channel bean. Note that \'ttl\' info is not available in
    the SyndFeed bean, thus it\'s lost when converting from WireFeed to
    SyndFeed.
3.  Change. RSS enclosures with empty \'length\' attributes are
    accepted\
    Parsing an RSS feed with an enclosure where the length attribute was
    an empty String were failing. Now they are parsed and the length is
    set to 0.
4.  Change. RSS 1.0 feeds use URI/Link for unique ID (rdf:about).\
    RSS 1.0 specification recommends that the rdf:about attribute URI
    use the value of the item\'s link element, though this could be
    different if the user chooses to override it by specifying their own
    URI. RSS 1.0 feeds now use the URI if specified, otherwise the link
    for the item.
5.  Fix. toString() was reporting NullPointerException with List
    properties\
    When a List (or Map) property had a NULL element the toString()
    logic was failing partially due to a NullPointerException.
6.  Fix. DC creator elements were being lost when converting to
    SyndFeed\
    DC creator elements were being lost when converting to SyndFeed.
    This was happening with RSS versions that have native author
    elements (0.94 and 2.0) and for the managingEditor element at
    channel level (available in 0.91 Userland and onwards).
7.  Change. Date and enumeration elements are trimmed during parsing\
    There are some feeds that add whitespaces or carriage return
    characters before or after the proper date or enumeration value.
    This was causing ROME to fail processing those elements. This is
    taken care now as all dates elements in all feed types and Modules
    and the \'channel.skipHours.hour\' and \'channel.skipDays.day\'
    (RSS0.91 - RSS2.0) are trimmed before parsing and setting their
    values in the beans.
8.  Fix. SyndFeed description now maps to atom:tagline\
    Previously, atom:info was being mapped to the feed\'s description.
    According to the Atom03 spec atom:info should be ignored by parsers,
    and the more appropriate element is atom:tagline.
9.  Fix. RSS cloud is now generated/parsed correctly\
    The \'path\' attribute from the cloud was not being
    generated/parsed. The parser now process all cloud attributes and
    set the cloud to the channel.
10. Fix. RFC-822 2 digit years\
    Previously RFC-822 dates did not work correctly with 2 digit years.
    This is now fixed.
11. Fix. No alternate link causes IndexOutOfBoundsException\
    Fix bug where no alternate link causes IndexOutOfBoundsException in
    ConverterForAtom03 (Thanks to Joseph Van Valen).
12. Change. Date parsing attemps RFC822 on W3C parsing on all feeds\
    All feed parsers (RSS and Atom) now attemp both RFC822 and W3C
    parsing on date values.
13. Fix. XmlFixerReader removes character from stream when parsing an
    entity that contains an invalid character\
    Fix bug in XmlFixerReader where an invalid entity such as \"&ent=\",
    gets put back on the stream without the last character (in this
    example, \"&ent=\" becomes \"&ent\"). This was most visible when the
    XmlFixerReader encountered an URL with a query string that has more
    than one parameter (e.g.
    [http://www.url.com/index.html?qp1=1&qp2=2](http://www.url.com/index.html?qp1=1&qp2=2))
    \-- all \"=\" after the first one would disappear.
14. Change. DateParser can use additional custom datetime masks\
    Besides attempting to parse datetime values in W3C and RFC822
    formats additional datetime masks can be specified in the
    /rome.properties files using the \'datetime.extra.masks\' property.
    To indicate multiple masks the \'\|\' character must be used, all
    other characters are considered part of the mask. As with
    parser/generators/converter plugins the masks are read from all
    /rome.properties file in the classpath.

### Known Issues

None.

### Dependencies

-   J2SE 1.4+
-   [JDOM 1.0](http://www.jdom.org/)

### Downloads

-   [rome-0.7.zip](rome-0.7.zip)
-   [rome-0.7.tar.gz](rome-0.7.tar.gz)
-   [rome-0.7-src.zip](rome-0.7-src.zip)
-   [rome-0.7-src.tar.gz](rome-0.7-src.tar.gz)
-   [rome-samples-0.7-src.zip](rome-samples-0.7-src.zip)
-   [rome-samples-0.7-src.tar.gz](rome-samples-0.7-src.tar.gz)
