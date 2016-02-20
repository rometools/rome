# ROME

ROME is a Java framework for RSS and Atom feeds. The framework consist of several modules:
| Module | Description |
| ------ | ----------- |
| rome-parent | is the parent project for all ROME modules and contains the common Maven configuration. |
| rome-utils | provides utility classes that are used in several ROME modules. |
| rome | is the main RSS and Atom library. It makes it easy to work with most syndication formats: RSS 0.90, RSS 0.91 Netscape, RSS 0.91 Userland, RSS 0.92, RSS 0.93, RSS 0.94, RSS 1.0, RSS 2.0, Atom 0.3, Atom 1.0. |
| rome-modules| enables rome to handle several feed extensions like MediaRSS, GeoRSS and others. |
| rome-opml | contains [OPML](https://en.wikipedia.org/wiki/OPML) parsers and tools. |
| rome-fetcher | is a caching feed fetcher that supports retrieval of feeds via HTTP conditional GET. Supports ETags, GZip compression, and RFC3229 Delta encoding. |
| rome-certiorem | is a [PubSubHubub](https://en.wikipedia.org/wiki/PubSubHubbub) implementation based on rome. |
| rome-certiorem-webapp | is an example webapp for rome-certiorem |
| rome-propono | supports publishing protocols, specifically the Atom Publishing Protocol and the legacy MetaWeblog API. Propono includes an Atom client library, an Atom server framework and a Blog client that supports both Atom protocol and the MetaWeblog API. |

## Changelog

### 1.5.1

- solved an [XML bomb](https://en.wikipedia.org/wiki/Billion_laughs) vulnerability

Important note: due to the security fix ROME now forbids all Doctype declarations by default. This will break compatibility with RSS 0.91 Netscape
because it requires a Doctype declaration. When you experience problems you have to activate the property **allowDoctypes** on the SyndFeedInput object. You 
should only use this possibility when the feeds that you process are absolutely trustful.

### 1.5.0

- many (untracked) enhancements
- code cleanup
- renamed packages (was required to be able to push to Maven Central after years again)
- updated sourcecode to Java 1.6

### Prior to 1.5.0

- see [http://rometools.github.io/rome/ROMEReleases](http://rometools.github.io/rome/ROMEReleases) 