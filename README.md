# ROME

ROME is a set of RSS and Atom Utilities for Java. It makes it easy to work in Java with most syndication formats: RSS 0.90, RSS 0.91 Netscape, 
RSS 0.91 Userland, RSS 0.92, RSS 0.93, RSS 0.94, RSS 1.0, RSS 2.0, Atom 0.3, Atom 1.0

More Information: http://rometools.github.io/rome/

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