## Rome Fetcher 0.3

Rome Fetcher version 0.3 is inital release of the Rome Fetcher. It is
released as version 0.3 to synchronize with the version number of the
core Rome project release.

### Downloads

-   [rome-fetcher-0.3-src.zip](./rome-fetcher-0.3-src.zip)
-   [rome-fetcher-0.3.tar.gz](./rome-fetcher-0.3.tar.gz)
-   [rome-fetcher-0.3.zip](./rome-fetcher-0.3.zip)
-   [rome-fetcher-0.3-src.tar.gz](./rome-fetcher-0.3-src.tar.gz)

### Tutorials

-   [Building the Rome Fetcher
    (fetcher)](../BuildingTheRomeFetcher.html)
-   [Using the Rome Fetcher module to retrieve feeds
    (fetcher)](../../getting-started/index.html)
-   [Sample programs included (fetcher)](../SampleProgramsIncluded.html)

### Known Issues

-   The Maven build does not run the `jetty` tests because of a bug in
    Maven
-   Version 0.3 does not have `Xerces` included in the project.xml (it
    is required to run the samples). Either get the latest `project.xml`
    from CVS, or [patch it
    yourself](https://rome.dev.java.net/source/browse/rome/subprojects/fetcher/project.xml?r1=1.1&r2=1.2)
-   0.3 had a bug that caused it to overwite system properties.

### Change Log

1.  Updated to handle removal of IO methods using byte streams\
    Byte Stream IO was removed from Rome itself. The Rome Fetcher is now
    updated to support this
2.  Add FeedFetcherI interface and FeedFetcherFactory class\
    There is now a FeedFetcherI interface, which FeedFetcher implements.
    Use FeedFetcherFactory to create instances of FeedFetcher (as
    suggested by Joseph Ottinger) (FeedFetcherFactory was later removed)
3.  Event Support Added to FeedFetcherI\
    The FeedFetcherI interface now supports feed polled, feed retrieved
    and feed unchanged events
4.  Samples added\
    Samples are now included with the Rome Fetcher
5.  Unit Tests Added\
    JUnit based tests which invoke the Rome Fetcher against an embedded
    Jetty webserver are now included
6.  Bug fixes in the FeedFetcher event model\
    The JUnit test suite uncovered some bugs in the event model used by
    the FeedFetcher. These bugs are now fixed.
7.  Refactored the SyndFeedInfo class\
    SyndFeedInfo now extends ObjectBean
8.  Removed FeedFetcherFactory\
    The benefit of the FeedFetcherFactory was arguable. Now the client
    code will need to manage the creation of specific implementations of
    the FeedFetcher
