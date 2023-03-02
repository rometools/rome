## Rome Fetcher 0.5

The ROME Fetcher v 0.6 is now released. This page exists for historical
purposes only.

### Downloads

-   [rome-fetcher-0.5-src.zip](./rome-fetcher-0.5-src.zip)
-   [rome-fetcher-0.5.zip](./rome-fetcher-0.5.zip)
-   [rome-fetcher-0.5.tar.gz](./rome-fetcher-0.5.tar.gz)
-   [rome-fetcher-0.5-src.tar.gz](./rome-fetcher-0.5-src.tar.gz)

### Tutorials

-   [Building the Rome Fetcher
    (fetcher)](../BuildingTheRomeFetcher.html)
-   [Using the Rome Fetcher module to retrieve feeds
    (fetcher)](../../getting-started/index.html)
-   [Sample programs included (fetcher)](../SampleProgramsIncluded.html)

### Known Issues

-   When listening to feed events using `FetcherListener`, there is no
    way to get to the retrieved content, because it is set after firing
    the event. \--
    [jawe](http://wiki.java.net/twiki/bin/view/Javawsxml/Jawe)
-   When listening to feed events using `FetcherListener`, the feed URLs
    returned by the `FetcherEvent` are prepended with
    \"sun.net.www.protocol.http.HttpURLConnection:\" \--
    [jawe](http://wiki.java.net/twiki/bin/view/Javawsxml/Jawe)

### Change Log

1.  SyndFeedInfo implements Serializable\
    SyndFeedInfo implements Serializable to make it easier to store
2.  Support for rfc3229 delta encoding\
    The Fetcher now supports rfc3229 delta encoding. See
    [http://www.ietf.org/rfc/rfc3229.txt](http://www.ietf.org/rfc/rfc3229.txt)
    and
    [http://bobwyman.pubsub.com/main/2004/09/using_rfc3229_w.html](http://bobwyman.pubsub.com/main/2004/09/using_rfc3229_w.html).
    Note that this is support is experimental and disabled by default
