## ROME 0.3 Beta (Jul 28, 2004)

### Changelog

1.  Changed loading mechanism for parsers, generators and converters\
    Previous mechanism was complicated and it wouldn\'t work in server
    environments where you cannot alter System properties at will.\
    Now there are no properties to set. Just including a rome.properties
    file in the root of a JAR file will make Rome to load any parser,
    generator or converter defined in the properties file and included
    in the JAR file (To be documented).
2.  Added Modules support to RSS 2.0 parser and generator\
    We were only looking for modules in RSS 1.0 and Atom 0.3. Now we
    also look in RSS 2.0.
3.  Modules parsers and generators are now per feed type\
    They were global, now each feed type parser and generator has it\'s
    own set of modules parsers and generators. They are configurable in
    the rome.properties file.
4.  All parsers, generators and converters are loaded once per
    definition\
    There were some cases we were loading them on every
    WireFeedParser/WireFeedGenerator instantiation. As they are all
    thread-safe we just use one instance.
5.  Changed some implementation (hidden) class names\
    Some typos corrections and adding consistency to the naming.
6.  Added Fetcher module\
    Added a HTTP fetcher with conditional get and gzip support (See
    modules/fetcher)
7.  Modified the samples module build to fix error\
    Previouly some people were having a maven error trying to run some
    of the samples. This should now be fixed
8.  Removed methods with byte stream signatures from Rome IO classes\
    They have no way to know what the encoding is or has to be and they
    are using the platform default. That does not always work, for
    example when doing HTTP where the default is ISO-8859-1.\
    Leaving the char streams one and let the developer to do the right
    thing (Rome cannot do any magic here).
9.  Added getModule(String uri) to SyndFeedI, Atom Feed and RSS Channel\
    There was not way to obtain a specific module using the module URI,
    you had to obtain the module list and iterate looking for the
    module. The getModule(string uri) is a convenience method in all the
    feed beans to do that.
10. New. Added \'encoding\' property to WireFeed and SyndFeedI/SyndFeed\
    Impact: It affects RSS, Atom and SyndFeed syndication beans.\
    It\'s not being set or use by parsers and generators as they always
    deal with a char strean where the charset is already set.\
    The converters, going from Channel/Feed 2 SyndFeed and vice versa
    are wired to pass the encoding if set.
11. Fix. CloneableBean array cloning bug\
    Impact: It affects all Rome beans as they all extend ObjectBean that
    uses CloneableBean\
    Arrays were not being cloned but copy by reference. This was
    affecting all Rome beans as they all extend ObjectBean.
12. Change. CloneableBean, Basic (primitives & string) types not cloned
    anymore\
    Impact: It affects all Rome beans as they all extend ObjectBean that
    uses CloneableBean\
    Basic types are inmutable, no need to clone them. As things are done
    using reflection there were unnecessary objects creation.
13. Change. CloneableBean, added Date to list of Basic types\
    Impact: It affects all Rome beans as they all extend ObjectBean that
    uses CloneableBean\
    Same reasoning as #12
14. Change/Fix. EqualsBeans, works on defined class\
    Impact: It affects all Rome beans as they all extend ObjectBean,
    which uses EqualsBean\
    EqualsBean checks for equality by comparing all properties. Until
    now it was doing this using all properties of the class implementing
    the bean. This behavior is not correct as implementations may have
    other properties than the defined in the public interface (and
    example is: a SyndFeedI implementation for Hibernate that has to
    have an ID field, and this would apply to all bean interfaces).\
    Because of this change, both EqualsBean and ObjectBean take a Class
    parameter in the constructor. Equals will limit the comparison for
    equality to the properties of that class. If there is not interface
    for the bean, just the bean implementation (this is the case of
    Channel and Feed), the implementation Class is passed
15. Change/Fix. ToStringBean, works on defined class\
    Same as #14 but on ToStringBean instead EqualsBean
16. New. Added copyFrom functionality to synd.\* and module.\* beans\
    Impact: It affect all synd.\* and module.\* beans and other classes
    that extend ObjectBean.\
    A new interface \'CopyFrom\' has been added to commons.\
    synd.\* and module.\* bean interfaces implement this interface and
    their implementations implement the copyFrom functionality.\
    The copyFrom functionality allows copying a complete bean feed from
    one implementation to another. The obvious use case is from the
    default bean implementation to a persistent aware (ie Hibernate)
    implementation.\
    The implementation uses the same pattern used by EqualsBean,
    ConeableBean, it\'s in the synd.impl.\* package, the supporting
    class is call SyndCopyFrom.\
    Note that SyndCategories does not support the copyFrom functionality
    as it\'s just a convenience way of accessing DCModule\'s subjects
    (where DCModule supports copyFrom). The short explanation is that
    categories still work and are there after a copyFrom.
17. Fix. WireFeed constructor was passing the WireFeed.class to
    ObjectBean\
    rss.Channel, atom.Feed & WeatherChannel constructor implementations
    only, no change to their signatures.\
    The class is used for property scanning for toString, equals
    behavior of the ObjectBean.
18. Fix. copyFrom was failing with Enum properties\
    It was not possible to do a copyFrom on a feed with SyModule data as
    it had Enum properties.\
    Wrong comparison of classes when checking basic types, in the case
    of enumeration classes has to check it extends Enum not equality.
19. Fix. SyndFeed and SyndEntry where losing Modules\
    If they had modules other than DCModule they were being dropped when
    accessing convenience methods (getCategories, getLanguage, etc).\
    The Synd\* convenience methods just map to the DCModule properties.
    If there is no DCModule a new one is created if needed. When this
    happened instead bean added to the list of current modules a new
    list with just the DCModule was being set.
20. New Sample and tutorial for creating a feed\
    A new sample and tutorial that creates and writes a feed has been
    added.
21. New Sample showing how to add a Module bean, parser and generator to
    Rome\
    This sample defines a dummy module for use with RSS 1.0 (it can work
    also with RSS 2.0 and Atom 0.3). Still have to write tutorial
22. Undoing change #13 as Date is not inmutable\
    The Date class is not inmutable, CloneableBean now clones Date
    properties.

### Known Issues

-   Same issues as Rome v0.1
-   When processing XML documents with DTD (ie: Netscape RSS 0.91) if
    the XML parser implementation is not Xerces and the system does not
    have access ot the internet, the XML parser will fail.
-   If the feed starts with a
    [BOM](http://www.unicode.org/faq/utf_bom.html#BOM)
    the JAXP SAX parser may fail, using [Xerces
    2.6.2](http://xml.apache.org/xerces2-j) addresses the
    problem.

### Dependencies

-   J2SE 1.4+
-   [JDOM Beta 10](http://www.jdom.org/)

### Downloads

-   [rome-0.3.zip](rome-0.3.zip)
-   [rome-0.3.tar.gz](rome-0.3.tar.gz)
-   [rome-0.3-src.zip](rome-0.3-src.zip)
-   [rome-0.3-src.tar.gz](rome-0.3-src.tar.gz)
-   [rome-samples-0.3-src.tar.gz](rome-samples-0.3-src.tar.gz)
-   [rome-samples-0.3-src.zip](rome-samples-0.3-src.zip)
