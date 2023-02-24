## Welcome to ROME

ROME is a Java framework for RSS and Atom feeds. It\'s open source and
licensed under the Apache 2.0 license.

ROME includes a set of parsers and generators for the various flavors of
syndication feeds, as well as converters to convert from one format to
another. The parsers can give you back Java objects that are either
specific for the format you want to work with, or a generic normalized
SyndFeed class that lets you work on with the data without bothering
about the incoming or outgoing feed type.

### ROME Subprojects

| Subproject | Purpose |
| --- | --- |
| [Modules](./Modules/index.html) | Provide support for feed extensions such as GeoRSS, iTunes, Microsoft SSE and SLE, Google GData and others. |
| [Fetcher](./Fetcher/index.html) (deprecated) |A caching feed fetcher that supports retrieval of feeds via [HTTP conditional GET](http://fishbowl.pastiche.org/2002/10/21/http_conditional_get_for_rss_hackers){.externalLink}. Supports ETags, GZip compression, and [RFC3229 Delta encoding](http://bobwyman.pubsub.com/main/2004/09/using_rfc3229_w.html){.externalLink}. |
| [Propono](./Propono/index.html) | Supports publishing protocols, specifically the Atom Publishing Protocol and the legacy MetaWeblog API. Propono includes an Atom client library, an Atom server framework and a Blog client that supports both Atom protocol and the MetaWeblog API. |
| [OPML](./Opml/index.html) | Outline Processor Markup Language (OPML) parser and tools. |

### Further information

-   [ROME Releases (rome)](./ROMEReleases/index.html)
-   Working with ROME
    -   [What part of the API you should be using
        (rome)](./WhatPartOfTheAPIYouShouldBeUsing.html)
    -   [Tutorials and Articles (rome)](./TutorialsAndArticles.html)
-   Articles about ROME
    -   [Project Motivation](./WhyThisProject.html) - why we started
        ROME
    -   [API FAQ](./RomeAPIFAQ.html), why things are like they are
    -   [Evaluation of existing RSS parsing
        libraries](./WhatSWrongWithOtherExistingRSSParsingLibraries.html)
-   Inside ROME, How Things Work
    -   [How ROME Works](./HowRomeWorks/index.html), Understanding ROME,
        a detailed overview by Dave Johnson (This doc is based on ROME
        v0.4)
    -   [ROME Plugins
        Mechanism](./RssAndAtOMUtilitiEsROMEV0.5AndAboveTutorialsAndArticles/RssAndAtOMUtilitiEsROMEPluginsMechanism.html),
        bootstrap, adding and changing parsers, generators, converters
        and modules
    -   [Feeds Date
        Elements](./RssAndAtOMUtilitiEsROMEV0.5AndAboveTutorialsAndArticles/FeedsDateElementsMappingToSyndFeedAndSyndEntry.html),
        how Date data is mapped to SyndFeed and SyndEntry
    -   [Feed and Entry URI
        Mapping](./RssAndAtOMUtilitiEsROMEV0.5AndAboveTutorialsAndArticles/FeedAndEntryURIMappingHowSyndFeedAndSyndEntryUriPropertiesMapToRSSAndAtomElements.html),
        how SyndFeed and SyndEntry \'uri\' properties map to concrete
        feed elements
    -   [XML Charset Encoding
        Detection](./RssAndAtOMUtilitiEsROMEV0.5AndAboveTutorialsAndArticles/XMLCharsetEncodingDetectionHowRssAndAtOMUtilitiEsROMEHelpsGettingTheRightCharsetEncoding.html),
        how ROME helps getting the right charset encoding
    -   [Creating a custom
        Module](./RssAndAtOMUtilitiEsROMEV0.5AndAboveTutorialsAndArticles/RssAndAtOMUtilitiEsROMEV0.5TutorialDefiningACustomModuleBeanParserAndGenerator.html),
        creating all necessary pieces, bean, parser and generator
    -   [The CopyFrom interface
        (rome)](./RssAndAtOMUtilitiEsROMEV0.5AndAboveTutorialsAndArticles/TheCopyFromInterface.html)
    -   [ROME bean utilities, equals, toString and
        cloning](./RssAndAtOMUtilitiEsROMEV0.5AndAboveTutorialsAndArticles/UnderstandingRssAndAtOMUtilitiEsROMEBeanUtilities.html)
    -   [Customizing Date and time
        parsing](./RssAndAtOMUtiliEsROMEV0.7DateAndTimeParsing.html) in
        the rome.properties file
    -   [Using ROME from Maven2](./ROMEAndMaven2.html)
    -   [Using ROME with OSGi](./ROMEAndOSGI.html)
    -   [Preserving Wire Feeds to obtain access to Atom/RSS specific
        fields](./PreservingWireFeeds.html)
-   ROME development
    -   [How to build ROME](./HowToBuildRome.html) (ROME uses
        [Maven](http://maven.apache.org/){.externalLink})
    -   [Changes Log](./ChangeLog.html), what and when
    -   [ROME Development Process (rome)](./ROMEDevelopmentProcess.html)
    -   [ROME Development Proposals
        (rome)](./ROMEDevelopmentProposals/index.html) - proposals for
        new ROME features and releases
