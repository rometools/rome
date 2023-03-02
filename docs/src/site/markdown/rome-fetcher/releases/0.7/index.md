## ROME Fetcher 0.7

### Downloads

-   [rome-fetcher-0.7-src.zip](./rome-fetcher-0.7-src.zip)
-   [rome-fetcher-0.7.tar.gz](./rome-fetcher-0.7.tar.gz)
-   [rome-fetcher-0.7.zip](./rome-fetcher-0.7.zip)
-   [rome-fetcher-0.7-src.tar.gz](./rome-fetcher-0.7-src.tar.gz)

### Tutorials

-   [Building the Rome Fetcher
    (fetcher)](../BuildingTheRomeFetcher.html)
-   [Using the Rome Fetcher module to retrieve feeds
    (fetcher)](../../getting-started/index.html)
-   [Sample programs included (fetcher)](../SampleProgramsIncluded.html)

### Known Issues

-   `HashMapFeedInfoCache` doesn\'t work quite right because
    `URL.hashCode()` does hostname resolution and treats virtual hosts
    with the same IP as equal, so e.g. all RSS feeds from `blogspot.com`
    collide in the cache. Also, it\'s really slow. Fix is to use
    `URL.toExternalForm()` as the hash key instead of the URL itself.

### Change Log

1.  Fix for URL Connection leak\
    In some circumstances URLConnection objects were not closed. This
    could cause problems in long-running application.
