## Content

This plug in is for use content:encoded in feeds.

### Sample Usage

```java
SyndFeedInput input = new SyndFeedInput();
SyndFeed syndfeed = input.build(new XmlReader(feed.toURL()));

Module module = syndfeed.getModule("http://purl.org/rss/1.0/modules/content/");
ContentModule content = (ContentModule) module;

Iterator it = content.getEncodeds().iterator();
System.out.println( it.next() );
```

### Changes

#### 0.4

Initial move to the ROME project.
