## Rome Fetcher 0.4

### Downloads

-   [rome-fetcher-0.4-src.zip](./rome-fetcher-0.4-src.zip)
-   [rome-fetcher-0.4.tar.gz](./rome-fetcher-0.4.tar.gz)
-   [rome-fetcher-0.4.zip](./rome-fetcher-0.4.zip)
-   [rome-fetcher-0.4-src.tar.gz](./rome-fetcher-0.4-src.tar.gz)

### Tutorials

-   [Building the Rome Fetcher
    (fetcher)](../BuildingTheRomeFetcher.html)
-   [Using the Rome Fetcher module to retrieve feeds
    (fetcher)](../../getting-started/index.html)
-   [Sample programs included (fetcher)](../SampleProgramsIncluded.html)

### Known Issues

-   No known issues (yet!)

### Change Log

1.  Refectored to match Rome naming standards\
    FeedFetcherI renamed to FeedFetcher\
    #. New FeedFetcher Implementation\
    HttpClientFeedFetcher uses the Apache Commons HTTP Client
2.  Abstract test classes excluded in project.xml\
    Tests now run correctly under Maven
3.  Added GZip support to HttpClientFeedFetcher\
    HttpClientFeedFetcher now supports GZip compression. Tests have been
    added.
