## ROME 0.6 Beta (Apr 01, 2005)

### Release Notes

-   Bug fixes

### Changelog

1.  Fix. W3C date-time parsing now handles date-time with \'Z\'
    modifier\
    The W3C date-time parser was not parsing times using the UTC
    modifier \'Z\'.
2.  Fix. XML prolog encoding parsing was failing when other attributes
    where present in the prolog\
    If there was an attribute following the encoding attribute the value
    of the encoding attribute was misinterpreted. For example, for the
    XML prolog the detected encoding was `UTF-8" standalone="yes`
    instead of `UTF-8`.
3.  Change. XmlReader lenient behavior gives priority to XML prolog
    encoding over content-type charset\
    In ROME 0.5 the XmlReader first attempts to do a strict charset
    encoding detection. Only if the strict detection fails it attempts a
    lenient detection. When the HTTP Content-Type header is of type
    `text/*xml` and the header does not specify any charset, RFC 3023
    mandates that the charset encoding must be `US-ASCII`. It\'s a
    common error for sites to use the `text/*xml` MIME type without
    charset information and indicate the charset encoding in the XML
    prolog of the document, being the charset encoding in the XML prolog
    different from `US-ASCII`. The XmlReader lenient behavior has been
    modified to give precedence to the XML prolog charset encoding, if
    present, over the HTTP Content-Type charset encoding.
4.  Addition. XML Healer\
    ROME parsers, SyndFeedInput and WireFeedInput, have a new feature,
    XML healing.\
    The XML healing trims the beginning of the XML text document if
    there are whitespaces, enters or XML comments before the XML prolog
    or the root element. It also replaces all HTML literal entities
    occurrences with coded entities. These changes convert feeds
    technically invalid (from the XML specification perspective) into
    valid ones allowing the SAX XML parser to successfully parse the XML
    if there are not other errors in it.\
    This behavior is active by default. It can be turned on and off
    using the new \'xmlHealerOn\' property in the SyndFeedInput and
    WireFeedInput classes.\
    The idea for this feature was taken from the FeedFilter from
    Jakarta\'s commons feedparser.
5.  Addition. The XML prolog of generated feeds contains the feed
    encoding\
    ROME generators were creating feeds with the XML prolog encoding
    always set to \'UTF-8\', if the given Writer had another charset
    encoding things would break for anybody consuming the feed (a
    mismatch between the char stream charset and what the XML doc
    says).\
    The SyndFeedOutput and WireFeedOutput generators now use the
    SyndFeed and WireFeed \'encoding\' property to set the \'encoding\'
    attribute in the XML prolog of the generated feeds. It is the
    responsibility of the developer to ensure that if the String is
    written to a character stream the stream charset is the same as the
    feed encoding property.
6.  Change. SyndFeed to Atom convertion now uses \'escaped\' mode for
    content elements\
    SyndFeed to Atom converter was using \'xml\' mode for content
    elements. This was breaking feeds with content that was not
    propertly escaped as it was assumed to be XML fragments.
7.  Change. RSS 2.0 parser and generator now handles DC Module\
    ROME configuration has been changed so RSS 2.0 parser and generator
    handle DC Module elements at channel level and item level.
8.  Fix. RSS0.93, RSS0.94 and RSS2.0 \'dc:date\' element value was being
    lost under certain conditions\
    If a feed had \'dc:date\' elements but not \'pubDate\' elements, the
    \'dc:date\' elements where lost when converting from Channel to
    SyndFeed.\
    This was happening for \'dc:date\' elements at channel level and at
    item level.
9.  Fix. RSS 1.0 \'rdf:resource\' and \'rdf:about\' item linking
    attributes use a unique ID now\
    The value for the \'rdf:resource\' and \'rdf:about\' linking
    attributes was done using the value of the \'link\' element. If
    there is more than one item with the same link the generated feed
    will be incorrect.\
    Instead using the link value now the index of the item is used for
    the linkage between \'rdf:resource\' and \'rdf:about\' for items.
10. Fix/Change. Parsing and setting of enumerated elements is case
    insentive now\
    Parsing and bean setting of enumerated values (such as RSS skipDay,
    Atom content mode, etc) are now case insentive, generation is strict
    (Postel Law).
11. Fix. Remove enumeration check on \'rel\' attribute of Atom link
    elements\
    Because a misunderstanding of Atom 0.3 specification the Atom Link
    bean was checking the value of the \'rel\' property against a set of
    valid values. The check has been removed.
12. Fix. DC subjects (in RSS versions with native categories) were being
    lost on conversion to SyndFeed\
    All RSS versions with native categories (at both channel and item
    level) now have the following behavior when converting to SyndFeed.\
    DC subjects are converted to SyndCategories. Native categories are
    converted to SyndCategories. They are both aggregated in a Set (to
    remove duplicates) then added to the SyndFeed.\
    When doing a SyndFeed to Channel conversion, if the RSS version has
    native categories and handles DC modules the categories will be
    duplicated as native and DC ones.
13. Fix/Change. RSS 1.0 rdf:about attribute in the channel.\
    RSS 1.0 uses the rdf:about attribute at the channel level as an
    identifier. This was not being parsed or generated (only supported
    at the item level). Support for this was added along with test
    cases.
14. Fix/Change/Addition. Multivalued Dublin Core element support.\
    Many feeds are using multiple DC elements to tag metadata, the
    interface for the DCModule has been changed to support Lists of
    elements, compatibility has been maintained with the existing
    interface though as the new methods are plural (creators vs.
    creator), the single value methods will remain as convenience
    methods. The implementation now uses the lists to represent all of
    the elements. The parser/generator modules for DC have been updated
    to reflect these changes along with a few other code cleanups in the
    DC\* modules.
15. Fix. Removed length constraint checks from RSS1.0 generator\
    RSS1.0 specification does not require, only suggests, maximum length
    for some of the elements. ROME was enforcing those lenghts when
    generating RSS1.0 feeds. This enforcement has been removed becuase
    is not mandatory.

### Known Issues

None.

### Dependencies

-   J2SE 1.4+
-   [JDOM 1.0](http://www.jdom.org/)

### Downloads

-   [rome-0.6.zip](rome-0.6.zip)
-   [rome-0.6.tar.gz](rome-0.6.tar.gz)
-   [rome-0.6-src.zip](rome-0.6-src.zip)
-   [rome-0.6-src.tar.gz](rome-0.6-src.tar.gz)
-   [rome-samples-0.6-src.tar.gz](rome-samples-0.6-src.tar.gz)
-   [rome-samples-0.6-src.zip](rome-samples-0.6-src.zip)
