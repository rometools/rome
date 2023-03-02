## ROME 0.2 Beta (Jun 16, 2004)

### Changelog

1.  FeedInput, added default constructor. Semantics is \'validation
    off\'\
    We forgot to added it when it was added to SyndFeed.\
    NOTE that validation is not implemented yet. We need
    DTDs/XML-Schemas for the different feed syndication types.
2.  FeedOutput and SyndOutput outputW3CDom() method typo correction\
    Fixed typo in outputW3CDom() method, it was ouptutW3CDom().
3.  AbstractFeed, renamed to WireFeed\
    Never liked Abstract in the name. From Java inheritance it makes
    sense, but from the syndication feed perspective doesn\'t. It\'s the
    super class of the 2 wire feed beans Rome has, RSS Channel and Atom
    Feed.
4.  Renamed SyndFeed createRealFeed(feedType) method to
    createWireFeed(feedType)\
    For consistency with change #3
5.  SyndFeed, added feedType property\
    Read/write property. It indicates what was the feed type of the
    WireFeed the SyndFeed was created from. And the feed type a WireFeed
    created with createWireFeed() will have.
6.  WireFeed, renamed type property to feedType\
    For consistency with #5. Also it\'s more clear what the type is
    about.
7.  Overloaded SyndFeed createRealFeed() with a no parameter signature
8.  FeedOutput, removed feed type from constructor\
    It now uses the WireFeed feedType property.
9.  SyndOutput, removed feed type from constructor\
    It now uses the SyndFeed feedType property define in #5.
10. FeedOutput, removed getType() method\
    Now FeedOutput uses the WireFeed feedType property.
11. Removed dependency on Jakarta Commons Codec library\
    We were using the Codec component to do Base64 encoding/decoding.
    Based on feedback to reduce component depencies we\'ve removed this
    one (yes, we implemented a Base64 encoder/decoder).
12. Removed dependency on Xerces library\
    This was an unnecessary dependency as Rome requeries JDK 1.4+ which
    includes JAXP implementation. JDOM can use that one.
13. Renamed syndication.io classes/interfaces\
    Renaming for naming consistency and to reflect on what type of feed
    they work on.
    ```
    FeedInput     --> WireFeedInput
    FeedOutput    --> WireFeedOutput
    FeedParser    --> WireFeedParser
    FeedGenerator --> WireFeedGenerator
    SyndInput     --> SyndFeedInput
    SyndOutput    --> SyndFeedOutputt
    ```
14. Removed syndication.util package, PlugableClasses is now private\
    The PlugableClasses class has no business in Rome public API, it\'s
    implementation specific, it has been hidden (it should be replaced
    later with a micro-container).
15. Added samples to the Rome project directory structure\
    Rome samples are a sub-project located at rome/modules/sample.

### Known Issues
-   Same issues as Rome v0.1

### Dependencies
-   J2SE 1.4+
-   [JDOM Beta 10](http://www.jdom.org/)

### Downloads
-   [rome-0.2.zip](rome-0.2.zip)
-   [rome-0.2.tar.gz](rome-0.2.tar.gz)
-   [rome-0.2-src.zip](rome-0.2-src.zip)
-   [rome-0.2-src.tar.gz](rome-0.2-src.tar.gz)
-   [rome-samples-0.2-src.tar.gz](rome-samples-0.2-src.tar.gz)
-   [rome-samples-0.2.tar.gz](rome-samples-0.2.tar.gz)
-   [rome-samples-0.2.zip](rome-samples-0.2.zip)
-   [rome-samples-0.2-src.zip](rome-samples-0.2-src.zip)
