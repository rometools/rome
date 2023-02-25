## Todo list

Please discuss items here on the rome dev mailing list

-   Automatically update the -[default User-Agent version
    number](https://github.com/rometools/rome-fetcher/blob/master/src/java/org/rometools/fetcher/FeedFetcher.java)
    via the build- Done: 23-June-2004
-   Listener Architecture (for URL changes via 3xx redirection etc)
    Done: 30-June-2004
-   [RSS
    Redirection](http://radio.userland.com/userGuide/reference/howToRedirectRss)
-   Unit Tests: smart unit tests along the lines of Mark Pilgrim\'s
    tests, in his -[Python Universal Feed
    Parser](https://pypi.org/project/feedparser/). He
    instantiates a web server to a local directory where the samples
    live, and then fetches the feeds from the server, which allows him
    to test in depth the behavior of gzip compression and etags
    handling.- Done 30-June-2004
-   Better character encoding handling - See
    [https://web.archive.org/web/20060706153721/http://diveintomark.org/archives/2004/02/13/xml-media-types
    (Archived)](https://web.archive.org/web/20060706153721/http://diveintomark.org/archives/2004/02/13/xml-media-types)
-   A caching feed fetcher
