## Welcome to ROME

ROME (**R**SS and At**om** utiliti**e**s) is a Java library for RSS and Atom 
feeds. It's open source and licensed under the Apache 2.0 license.

ROME includes a set of parsers and generators for the various flavors of 
syndication feeds, as well as converters to convert from one format to another. 
The parsers can give you back Java objects that are either specific for the 
format you want to work with, or a generic normalized SyndFeed class that lets 
you work on with the data without bothering about the incoming or outgoing feed 
type.

### Modules

| Module | Description |
| --- | --- |
| [Certiorem](rome-certiorem/index.html) (deprecated) | [PubSubHubub](https://en.wikipedia.org/wiki/WebSub) protocol for ROME. |
| [Fetcher](rome-fetcher/index.html) (deprecated) |A caching feed fetcher that supports retrieval of feeds via [HTTP conditional GET](http://fishbowl.pastiche.org/2002/10/21/http_conditional_get_for_rss_hackers). Supports ETags, GZip compression and [RFC3229 Delta encoding](https://en.wikipedia.org/wiki/Delta_encoding#Delta_encoding_in_HTTP). |
| [Modules](rome-modules/index.html) | Provide support for feed extensions such as GeoRSS, iTunes, Microsoft SSE and SLE, Google GData and others. |
| [OPML](rome-opml/index.html) | Outline Processor Markup Language (OPML) parser and tools. |
| [Propono](rome-propono/index.html) (deprecated) | Supports publishing protocols, specifically the Atom Publishing Protocol and the legacy MetaWeblog API. Propono includes an Atom client library, an Atom server framework and a Blog client that supports both the Atom protocol and the MetaWeblog API. |

### Motivation

Various flavors of RSS and Atom syndication formats were reaching a tipping 
point in 2004. At Sun we started various projects involving these Syndication 
formats, but when looking around for Java libraries to take care of the parsing
and generation of RSS we were not satisfied with what we found. ROME was started
out of this frustration.

Our requirements are to **ESCAPE** the Syndication Feeds Hell. In order to allow
that the library must be:

-   **E**asy to use: given a url, get back a feed object independent of
    the underlying format, and serialize the feed object to the format I
    want.
-   **S**imple: RSS initially stood for "Really Simple Syndication" *,
    and this simplicity is what made the format successful.
    Specifications wars have made the current situation much more
    complicated. The goal of the library is to give that simplicity back
    to developers: each API we use force on us a mental model of the
    domain and we are using more and more libraries on each project we
    implement. This library tries to ease the cognitive load of
    developers and provides a very simple model for feeds and entries,
    abstarcting out the details of the various underlying formats.
-   **C**omplete: must handle all versions of RSS and Atom
-   **A**bstract: provides a Java-friendly abstraction layer on top of
    the various syndication specifications, that maps the commonalities
    of the various feed formats into a single simple JavaBeans Data
    Model.
-   **P**owerful: lets me access all the metadata of the feeds
    regardless of their format. If I need them, lets me access optional
    metadata expressed in extensions accepted by various formats (RSS
    1.0 modules, other namespaces in Atom).
-   **E**xtensible: It needs to define a simple pluggable architecture
    to provide support for future extensions of the formats.

\* not so, it was originally \"RDF Site Summary\"

We set out to create this library in the same spirit as the
[JDOM](http://www.jdom.org/mission/index.html) library for XML manipulation in 
Java, incorporating XOM\'s 
[Elliotte Rusty Harold\'s pearls of wisdom about API design and refactoring](http://www.artima.com/intv/jdom.html) 
(see [Air Bags and Other Design Principles](http://www.artima.com/intv/airbags.html) which links his 6 interviews with Bill Venners). 
ROME itself uses JDOM.
