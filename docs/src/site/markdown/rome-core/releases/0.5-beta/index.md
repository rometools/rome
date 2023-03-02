## ROME 0.5 Beta (Jan 10, 2005)

### Release Notes

-   Removed common package and classes from the public API
-   Removed Enum class, using constants now
-   XmlReader now has a lenient behavior for charset encoding detection
-   Bug fixes

### Changelog

1.  Change. Got rid of Enum class\
    All constants in the beans are Strings now, the corresponding
    property setters check that the value being set is one of the valid
    constants. Rome has not business defining an Enum class.
2.  Change. Got rid of ToString interface\
    This is just an implementation convenience, it was polluting Rome
    API. Modified ToStringBean to work without requiring an interface
    and method to propagate the prefix to use with properties.
3.  Change. ObjectBean, ToStringBean, EqualsBean are not part of the
    public API anymore\
    These are just an implementation convenience, they were polluting
    Rome API. Rome bean implementations don\'t extends ObjectBean
    anymore. Instead they use it in a delegation pattern. While these
    classes are not public anymore they are part of Rome implementation.
4.  Change. CopyFrom interface moved to com.sun.syndication.feed
    package\
    The common package is gone now that the \*Bean classes are not there
    anymore. No point keeping a package just for an interface.
5.  Fix. PluginManager was not doing plugin lookup in the defined order\
    PluginManager (manages parsers, generators and convertors for feeds
    and modules) was not doing the lookup in order the plugins are
    defined in the rome.properties files. This is needed for parsers
    where the lookup involves detecting the feed type and the feed type
    detection goes needs to go from stronger to weaker.
6.  Addition. Rome now recognizes RSS 2.0 feeds with
    \'http://backend.userland.com/rss2\' namespace\
    These namespace was defined by an RSS 2.0 draft and later was
    dropped. There are feeds out there using this namespace and Rome was
    not parsing them.
7.  Change. By default XmlReader does a lenient charset encoding
    detection.\
    If the charset encoding cannot be determined per HTTP MIME and XML
    specifications the following relaxed detection is performed: If the
    content type is \'text/html\' it replaces it with \'text/xml\' and
    tries the per specifications detection again. Else if the XML prolog
    had a charset encoding that encoding is used. Else if the content
    type had a charset encoding that encoding is used. Else \'UTF-8\' is
    used.\
    There are 2 new constructors that take a lenient flag to allow
    strict charset encoding detection. If scrict charset encoding
    detection is performed and it fails an XmlReaderException is thrown
    by the constructor. The XmlReaderException contains all the charset
    encoding information gathered from the stream including the
    unconsumed stream.

### Known Issues

None.

### Dependencies

-   J2SE 1.4+
-   [JDOM 1.0](http://www.jdom.org/)

### Downloads

-   [rome-0.5.zip](rome-0.5.zip)
-   [rome-0.5.tar.gz](rome-0.5.tar.gz)
-   [rome-0.5-src.zip](rome-0.5-src.zip)
-   [rome-0.5-src.tar.gz](rome-0.5-src.tar.gz)
-   [rome-samples-0.5-src.tar.gz](rome-samples-0.5-src.tar.gz)
-   [rome-samples-0.5-src.zip](rome-samples-0.5-src.zip)
