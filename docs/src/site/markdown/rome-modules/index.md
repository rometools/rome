## ROME Modules

The ROME Modules project is an effort to roll up contributed module
support into a single distribution for users.

### Current modules in the subproject include:

-   [iTunes Podcasting](./ITunesPodcasting.html) -- Apples
    extensions for listing in the iTunes podcast directory.
-   [GeoRSS](./GeoRSS.html) -- For adding location information
    to RSS/Atom.
-   [Slash](./Slash.html) -- Used by Slash-based blogs.
-   [Google Base](./GoogleBase.html) -- For working with the
    Google Base types and custom tagsets.
-   [Content](./Content.html) -- For using content:encoded
    tags.
-   [Creative Commons](./CreativeCommons.html) -- A unified
    module for working with the RDF and RSS/Atom Creative Commons
    License information.
-   [Yahoo! MediaRSS](./MediaRSS.html) -- For working with Yahoo!
    MediaRSS feeds (and Flickr Photostreams)
-   [iPhoto Photocasting](./IPhotoPhotocasting.html) -- For
    working with Apple iPhoto Photocasts
-   [A9 OpenSearch](./A9OpenSearch.html) -- For working with
    Amazon/OpenSearch.org results.
-   [Microsoft Simple List Extensions
   ](./MicrosoftSimpleListExtensions.html) -- for sorting and
    grouping entries.
-   [Microsoft Simple Sharing Extensions
   ](./MicrosoftSimpleSharingExtensions.html) -- for
    synchronizing across bi-directional RSS and [OPML](./index.html)
    feeds.
-   [Yahoo! Weather](./Weather.html) -- For use with the Yahoo Weather
    API

### Get it.

The 0.3.2 release of modules.jar (which contains all the above modules)
is available.

### Downloads

-   [rome-modules-1.0.jar](rome-modules-1.0.jar)
-   [modules-0.3.2-javadoc.jar](modules-0.3.2-javadoc.jar)
-   [modules-0.3.2.jar](modules-0.3.2.jar)
-   [modules-0.3.1.jar](modules-0.3.1.jar)
-   [modules-0.3.1-javadoc.jar](modules-0.3.1-javadoc.jar)
-   [modules-0.3.1-sources.jar](modules-0.3.1-sources.jar)
-   [modules-0.3-sources.jar](modules-0.3-sources.jar)
-   [modules-0.3.2-sources.jar](modules-0.3.2-sources.jar)
-   [modules-0.3.jar](modules-0.3.jar)
-   [modules-0.3-javadoc.jar](modules-0.3-javadoc.jar)

Please don't use modules-0.3.jar. It had errors in the packaging which
have been fixed in modules-0.3.1.jar and subsequent releases

See individual module sites for information.

### Changes

#### 1.0 - released 24 February 2011

Finalized 1.0 release.

#### 0.3.2 - released 30 Jan 2009

-   [http://java.net/jira/browse/ROME-122](http://java.net/jira/browse/ROME-122)

### General Guidelines for modules:

This is intended to serve as a guide for contributions as well as a hint
for users working with the modules.

-   Modules are packaged in com.rometools.rome.feed.module.\[Module
    Name\].
-   Modules contain a com.rometools.rome.feed.module.\[Module
    Name\].Module.URI reference for retrieval from ROME Synd\* classes.
-   Modules contain a com.rometools.rome.feed.module.\[Module
    Name\].ModuleImpl that is a concrete implementation.
-   Modules contain a com.rometools.rome.feed.module.\[Module
    Name\].types package that holds any other datatypes needed for the
    module. Many of these are simple immutable types to simplify clone()
    and copyFrom()
-   Modules contain a com.rometools.rome.feed.module.\[Module Name\].io
    package with parsers and generators.
