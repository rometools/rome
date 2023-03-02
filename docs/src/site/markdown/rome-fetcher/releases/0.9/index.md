## ROME Fetcher 0.9

Note that there was no 0.8 Fetcher release

### Downloads

-   [rome-fetcher-0.9-src.zip](./rome-fetcher-0.9-src.zip)
-   [rome-fetcher-0.9.tar.gz](./rome-fetcher-0.9.tar.gz)
-   [rome-fetcher-0.9.zip](./rome-fetcher-0.9.zip)
-   [rome-fetcher-0.9-src.tar.gz](./rome-fetcher-0.9-src.tar.gz)

### Tutorials

-   [Building the Rome Fetcher
    (fetcher)](../BuildingTheRomeFetcher.html)
-   [Using the Rome Fetcher module to retrieve feeds
    (fetcher)](../../getting-started/index.html)
-   [Sample programs included (fetcher)](../SampleProgramsIncluded.html)

### Issues

-   [Known Issues (fetcher)](../KnownIssues.html)

### Change Log

1.  Fix for potential synchronization issue\
    There was the possibility of synchronization issues in the
    FeedFetcher. Fixed, thanks to suggestions from Javier Kohen.
2.  New LinkedHashMapFeedInfoCache FeedFetcherCache implementation\
    The new LinkedHashMapFeedInfoCache has the advantage that it will
    not grow unbound
