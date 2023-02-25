## ROME 0.4 Beta

### What is New, Highlights

-   Changed naming convention of bean interfaces and implementations
    (i.e.: SyndFeedI/SyndFeed are now SyndFeed/SyndFeedImpl)
-   New XmlReader that handles charset encoding as defined by the XML
    1.0 specification and RFC 3023
-   Support of RSS 0.91 Netscape and RSS 0.91 Userland as distinct feed
    types
-   All feed types have Modules support
-   Added checks to generators reducing the chances of generating
    invalid feeds
-   Comprehensive Unit testing
-   Bug fixes
-   More documentation and samples
-   Dependencies, upgraded to JDom 1.0 and removed Xerces

### Dependencies

-   J2SE 1.4+
-   [JDOM 1.0](http://www.jdom.org/)

### Downloads

-   [rome-0.4-src.zip](./rome-0.4-src.zip)
-   [rome-0.4.tar.gz](./rome-0.4.tar.gz)
-   [rome-0.4.zip](./rome-0.4.zip)
-   [rome-0.4-src.tar.gz](./rome-0.4-src.tar.gz)
-   [rome-samples-0.4-src.tar.gz](./rome-samples-0.4-src.tar.gz)
-   [rome-samples-0.4-src.zip](./rome-samples-0.4-src.zip)

### Additional Information

-   [Tutorials](./RomeV0.4Tutorials/index.html)
-   [Changes Log](../../ChangeLog.html)
-   Inside ROME, How Things Work
    -   [How ROME Works](../../HowRomeWorks/index.html), Understanding
        Rome (a detailed overview by Dave Johnson)
    -   [Rome common
        Package](../../HowRomeWorks/UnderstandingTheRomeCommonClassesAndInterfaces.html),
        bean utilities, enums, copying and clonning
    -   [ROME Plugins
        Mechanism](../../RssAndAtOMUtilitiEsROMEV0.5AndAboveTutorialsAndArticles/RssAndAtOMUtilitiEsROMEPluginsMechanism.html),
        bootstrap, adding and changing parsers, generators, converters
        and modules
    -   [Feeds Date
        Elements](../../RssAndAtOMUtilitiEsROMEV0.5AndAboveTutorialsAndArticles/FeedsDateElementsMappingToSyndFeedAndSyndEntry.html),
        how Date data is mapped to SyndFeed and SyndEntry
    -   [Feed and Entry URI
        Mapping](../../RssAndAtOMUtilitiEsROMEV0.5AndAboveTutorialsAndArticles/FeedAndEntryURIMappingHowSyndFeedAndSyndEntryUriPropertiesMapToRSSAndAtomElements.html),
        how SyndFeed and SyndEntry \'uri\' properties map to concrete
        feed elements
    -   [XML Charset Encoding
        Detection](../../RssAndAtOMUtilitiEsROMEV0.5AndAboveTutorialsAndArticles/XMLCharsetEncodingDetectionHowRssAndAtOMUtilitiEsROMEHelpsGettingTheRightCharsetEncoding.html),
        how Rome helps getting the right charset encoding

### Known Issues

None.
