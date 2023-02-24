::: section
## iTunes Podcasting

This plug in is for use with iTunes Music Service podcast listings.

::: section
### Sample Usage

```java
    SyndFeedInput input = new SyndFeedInput();
    SyndFeed syndfeed = input.build(new XmlReader(feed.toURL()));

    Module module = syndfeed.getModule("http://www.itunes.com/dtds/podcast-1.0.dtd");
    FeedInformation feedInfo = (FeedInformation) module;

    System.out.println( feedInfo.getImage() );
    System.out.println( feedInfo.getCategory() );

    // Or to create a feed..

    ArrayList modules = new ArrayList();
    EntryInformation e = new EntryInformationImpl();
    e.setDuration( new Duration( 10000 ) );
    modules.add( e );
    syndEntry.setModules( modules );
```

::: section
### Changes

::: section
#### 0.4

-   Corrected some Feed vs Item attribute issue.
-   Keywords now comma separated instead of space.
:::

::: section
#### 0.3

-   Added support for the \"other\" case of podcasts. It will now
    correctly parse all lowercase URIs as wells as the original mixed
    case URIs All generated feeds use the lowercase URI. Fixed some
    entity problems related to the apple summary tag.
:::
:::
:::
