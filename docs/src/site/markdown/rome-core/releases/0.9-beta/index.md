## ROME 0.9 Beta (Dec 11, 2006)

### Release Notes

-   Better support for RSS feeds that use
-   Better mapping for RSS description/content and Atom summary/content
-   Numerous bug fixes

### Changelog

1.  Design changes
    -   Support Atom feed.title, feed.subtitle and entry.title [Issue
        48](http://java.net/jira/browse/ROME-48)\
        #48 fixed via better support for Atom text constructs title and
        subtitle. Added get/setTitleEx() and get/setSubtitleEx(), which
        get get SyndContent objects. Title and subtitle still available
        from old getters/setters.
    -   Support for mapping Atom summary/content to RSS
        description/content
        [https://rome.dev.java.net/servlets/ReadMsg?list=dev&msgNo=1680](https://rome.dev.java.net/servlets/ReadMsg?list=dev&msgNo=1680)
    -   Fixed by introduced Content object in RSS model. ROME now parses
        as RSS Content. That makes parsing easier and allows us to
        support a more logical summary/content mapping:
        -   RSS to/from Atom
        -   RSS to/from Atom
2.  General parsing fixes
    -   XmlReader xml prolog regular expression does not allow for
        single quotes [Issue
        36](http://java.net/jira/browse/ROME-36)\
        The XmlReader was only parsing prolog encodings within double
        quotes, the regular expression to detect the encoding has been
        change to detect single or double quotes.
    -   Fix. XML prolog parsing now support whitespaces around \'=\'\
        If the XML prolog contained spaces around the \'=\' between the
        encoding attribute name and the encoding attribute value the
        encoding was not being detected. The fix accepts all valid
        whitespace characters (as defined in the XML spec).
    -   RSS parser does not recognize version=\"2.00\" [Issue
        33](http://java.net/jira/browse/ROME-33)
    -   Atom 1.0 Text Types Not Set Correctly [Issue
        39](http://java.net/jira/browse/ROME-39)
    -   Security issue [Issue
        46](http://java.net/jira/browse/ROME-46)
    -   Fix for the potential problem outlined in
        [http://www.securiteam.com/securitynews/6D0100A5PU.html](http://www.securiteam.com/securitynews/6D0100A5PU.html).
        Thanks to Nelson Minar for bringing this to our attention.
    -   Fix. Wrong default description type for RSS 2.0 Fix for [Issue
        26](http://java.net/jira/browse/ROME-26)
    -   Change default description type for RSS 2.0 from text/plain to
        text/html as per RSS 2.0 spec
    -   Fix to add all HTML4 entities, according to
        [http://www.w3.org/TR/REC-html40/sgml/entities.html](http://www.w3.org/TR/REC-html40/sgml/entities.html)
        specially for the HTMLsymbol set (Mathematical, Greek and
        Symbolic characters for HTML) and the HTMLspecial set (Special
        characters for HTML).
3.  Date parsing fixes
    -   Additional version and date leniency could extract more
        information [Issue
        24](http://java.net/jira/browse/ROME-24)
    -   Non RFC822 Dates not processed in RSS pubDate field [Issue
        27](http://java.net/jira/browse/ROME-27)
        -   RSS feed parsers were were only parsing RFC822 dates because
            they were not using the proper date-time parsing function
            for the date-time elements.
        -   If a W3C date-time element had no time component it was
            being parsed as local time instead of GMT, ROME DateParser
            class has been modified to use GMT in this situation.
        -   Current JDKs do not handle \'UT\' timezone indicator, ROME
            DateParser class has been modified to handle it.
        -   Use Atom updated instead of published [Issue
            41](http://java.net/jira/browse/ROME-41)
        -   Atom 1.0 Date (Updated or Published) Not Set [Issue
            42](http://java.net/jira/browse/ROME-42)
        -   lastBuildDate does not populate publishedDate [Issue
            43](http://java.net/jira/browse/ROME-43)
            Provides a feed date for RSS 0.91 feeds that specify
            lastBuildDate but not pubDate.
    -   Fix. Parsing some numeric elements was failing due to
        whitespaces The image.width and image.height of RSS091U, the
        frequency of SyModule and the cloud.port of RSS092 elements are
        now being trimmed before doing the integer parsing.
4.  Atom link and URI fixes
    -   Improper relative link resolution in Atom10Parser [Issue
        37](http://java.net/jira/browse/ROME-37)
    -   ATOM 1.0 Entry links parsing [Issue
        38](http://java.net/jira/browse/ROME-38)
    -   ConverterForRSS10.java does not set URI for item [Issue
        25](http://java.net/jira/browse/ROME-25)
    -   Valid IRI href attributes are stripped for atom:link [Issue
        34](http://java.net/jira/browse/ROME-34)
5.  Module fixes
    -   iTunes Module has incorrect author and category support [Issue
        35](http://java.net/jira/browse/ROME-35)
    -   mediarss.io.MediaModuleParser NumberFormatException [Issue
        45](http://java.net/jira/browse/ROME-45)
    -   Slash module not serializable for FeedFetcher [Issue
        44](http://java.net/jira/browse/ROME-44)

### Known Issues

None.

### Dependencies

-   J2SE 1.4+
-   [JDOM 1.0](http://www.jdom.org/)

### Downloads

-   [rome-0.9.zip](rome-0.9.zip)
-   [rome-0.9.tar.gz](rome-0.9.tar.gz)
-   [rome-0.9-src.zip](rome-0.9-src.zip)
-   [rome-0.9-src.tar.gz](rome-0.9-src.tar.gz)
-   [rome-samples-0.9.zip](rome-samples-0.9.zip)
-   [rome-samples-0.9.tar.gz](rome-samples-0.9.tar.gz)
-   [rome-samples-0.9-src.zip](rome-samples-0.9-src.zip)
-   [rome-samples-0.9-src.tar.gz](rome-samples-0.9-src.tar.gz)
