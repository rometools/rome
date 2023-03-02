## ROME 0.8 Beta (Feb 01, 2006)

### Release Notes

-   ROME now supports the final version of the Atom Syndication Format
    [RFC 4287](http://www.ietf.org/rfc/rfc4287): let\'s
    Nuke all these feeds! **NEW**
-   Enclosure support at the Synd level, for all our podcaster friends
-   Bug fixes
-   Modules galore: Content, iTunes Podcast, Slash, Google Base,
    Creative Commons, MediaRSS

### Changelog

1.  Change. Added enclosure support at Synd\* level\
    A new bean for handling enclosures at Synd\* level has been created
    (SyndEnclosure/SyndEnclosureImpl, interface/implementation).\
    The SyndEntry/SyndEntryImpl bean has a new \'enclosures\' property
    which returns the list of enclosures for that item.\
    The Wire\* to Synd\* converters for RSS propagate enclosures in both
    directions.\
    This enables handling enclosures from RSS 0.92, 0.93, 0.94 and 2.0
    at Synd\* level\
    Test cases have been modified to cover enclosures at Synd\* level.
2.  Change/Fix. Synd\* - Atom entry dates mapping
    -   (Change) Atom entries have 3 dates, \'modified\', \'issued\' and
        \'created\'. Synd entries have only 1 date property
        \'publishedDate\'. When converting from Atom to Synd the first
        not null date in the order above will be the one set in the Synd
        entry bean.
    -   (Fix) When converting from Synd to Atom the Synd entries
        \'publishedDate\' property value is set in both \'modified\' and
        \'issued\' properties of the Atom entry.\
        This Change/Fix is to be aligned with the Atom 0.3 spec.
3.  Fix. Trim enclosure length attribute\
    Fix from Trey Drake: At least 1 podcast site (ESPN) occasionally
    leaves trailing spaces in the enclosure content length attribute.
    This causes a NumberFormatException.
4.  Fix. Conversion to RSS 1.0 if Channel URI is not specified\
    Fix for problem converting to RSS 1.0 if not URI is specified at the
    channel level (it will now attempt to use the Link element)
5.  Changes to support Atom 1.0
    -   In com.sun.syndication.synd, added SyndLink and SyndPerson.
    -   In SyndEntry added. In SyndEntry, added summary, updatedDate,
        links collection and support for multiple authors.
    -   In com.sun.syndication.synd.impl, added Atom10Parser.java,
        Atom10Generator.java and ConverterForAtom10.java.
### Known Issues

None.

### Dependencies

-   J2SE 1.4+
-   [JDOM 1.0](http://www.jdom.org/)

### Downloads

-   [rome-0.8.zip](rome-0.8.zip)
-   [rome-0.8.tar.gz](rome-0.8.tar.gz)
-   [rome-0.8-src.zip](rome-0.8-src.zip)
-   [rome-0.8-src.tar.gz](rome-0.8-src.tar.gz)
