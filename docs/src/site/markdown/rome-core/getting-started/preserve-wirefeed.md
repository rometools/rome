## Preserve WireFeed

WireFeeds will be preserved if the property preserveWireFeed is set on
the SyndFeedInput object it is built from:

```java
SyndFeedInput in = new SyndFeedInput();
in.setPreserveWireFeed(true);
SyndFeed syndFeed = in.build(..);
WireFeed wireFeed = syndFeed.originalWireFeed();
```

Atom/RSS Entry/Item objects are also available from SyndEntry objects if
the WireFeed is preserved:

```java
Object obj = syndEntry.getWireEntry();
if (obj != null && obj instanceof Entry) {
    // it is an Atom Entry object
    // do Atom specific stuff, eg:
    Entry entry = (Entry) o;
    System.out.println(entry.getRights());
} else if (obj != null && obj instanceof Item) {
    // it is a RSS Item object
    // do RSS specific stuff eg:
    Item item = (Item) o;
    System.out.println(item.getComments());
}
```

The Fetcher can be set to preserve WireFeeds by setting the
preserveWireFeed property to true:

```java
feedFetcher.setPreserveWireFeed(true);
```

\-- Main.nicklothian - 11 Mar 2009
