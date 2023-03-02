## ROME Fetcher 1.0 RC2

Note that there was no 1.0 RC1 Fetcher release

### Downloads

-   [rome-fetcher-1.0RC2-src.zip](./rome-fetcher-1.0RC2-src.zip)
-   [rome-fetcher-1.0RC2.jar](./rome-fetcher-1.0RC2.jar)
-   [rome-fetcher-1.0RC2-javadoc.jar](./rome-fetcher-1.0RC2-javadoc.jar)
-   [rome-fetcher-1.0RC2-sources.jar](./rome-fetcher-1.0RC2-sources.jar)

### Tutorials

-   [Building the Rome Fetcher
    (fetcher)](../BuildingTheRomeFetcher.html)
-   [Using the Rome Fetcher module to retrieve feeds
    (fetcher)](../../getting-started/index.html)
-   [Sample programs included (fetcher)](../SampleProgramsIncluded.html)

### API Docs

-   [Fetcher API Docs](./rome-fetcher-1.0RC2-javadoc.jar)

### Issues

-   [Known Issues (fetcher)](../KnownIssues.html)

### Change Log

1.  BeanInfo class added for AbstractFeedFetcher\
    com.rometools.rome.fetcher.impl.AbstractFeedFetcherBeanInfo was
    created to allow introspection to correctly find the events
2.  Callback to allow access to HttpClient HttpMethod object\
    Add a HttpClientMethodCallbackIntf to allow the calling code to
    modify the HttpClient HttpMethod used to make the request (eg, add
    additinal headers, etc.) Also fixes a reported bug where the user
    agent wasn\'t being set properly
3.  Support for clearing cache\
    See
    [http://java.net/jira/browse/ROME-119](http://java.net/jira/browse/ROME-119)
    for details
