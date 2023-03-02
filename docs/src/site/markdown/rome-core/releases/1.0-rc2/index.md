## ROME 1.0 RC2 (Jan 09, 2009)

### Release Notes

-   ROME uses Maven 2 now
-   ROME jars are now available in the java.net Maven repository
-   More lenient handling of Number formats during parsing
-   Better date parsing for Atom dates

### Changelog

1.  Fix. Date parsing for Atom10 entry and additional W3C masks\
    Item date elements were being parsed with the W3C parser instead the
    lenient one (RFC822 + W3C + custom masks).\
    The following masks were added to W3C masks to handle RFC822
    timezone (ie \'-800\'):

    ```
    yyyy-MM-dd'T'HH:mm:ssZ yyyy-MM-dd't'HH:mm:sszZ
    ```
2.  Fix. Contributors properties in SyndEntry were not implementing the
    semantics of list properties.\
    They were returning NULL instead, now they return an empty list if
    not values are set.
3.  Fix. Contributors properties in SyndEntry and SyndFeed were not
    being converted to/from WireFeed
4.  Fix. Syndication Module Generator was failing if some of its values
    were null.\
    Checks for nulll have been added it to the generator to prevent
    NullPointerExceptions
5.  New. Added new constructor to XmlReader

    ```java
    public XmlReader(InputStream is, boolean lenient, String defaultEncoding)
    ```
6.  New. Support atom person construct extensions, using the Extendable
    interface on SyndPerson:\
    Patch from James Roper. See [Issue
    1101](http://java.net/jira/browse/ROME-110) for
    details
7.  New. Maven 2 build for main project\
    ROME can now be built with Maven 2
8.  New. OSGi support\
    OSGi headers to MANIFEST.MF so that rome.jar can also be used in an
    OSGi environment. See [Issue
    117](http://java.net/jira/browse/ROME-117) for
    details.
9.  New. Allow pretty printing to be turned on and off\
    see [Issue 114](http://java.net/jira/browse/ROME-114)
    for details. Thanks to Martin Kurz for the patch.
10. Configurable classloading behavior for OSGi compatibility.\
    We have received a report of some issues with plugin loading in an
    OSGi environment ([Issue
    118](http://java.net/jira/browse/ROME-118)). The fix
    appears to be to change Class.forName to classLoader.loadClass, but
    the semantics for this are subtly different, so we have made this
    new behavior user selectable. Set the
    \"rome.pluginmanager.useloadclass\" system property to \"true\" to
    enable it.
11. More lenient number parsing\
    There were a number of problems with feeds providing blank or
    invalid values in fields which would be numbers. ROME will now
    handles these better. See issues
    [104](http://java.net/jira/browse/ROME-104),
    [107](http://java.net/jira/browse/ROME-107) and
    [108](http://java.net/jira/browse/ROME-108) for
    details.

### Dependencies

-   [J2SE](http://wiki.java.net/twiki/bin/view/Javawsxml/J2SE) 1.4+
-   [JDOM 1.0](http://www.jdom.org/)

### Downloads

-   [rome-1.0RC2.jar](rome-1.0RC2.jar)
-   [rome-1.0RC2-sources.jar](rome-1.0RC2-sources.jar)
-   [rome-1.0RC2-javadoc.jar](rome-1.0RC2-javadoc.jar)
