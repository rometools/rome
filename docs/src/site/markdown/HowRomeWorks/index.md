## How Rome works

**Dave Johnson ([The Roller
Weblogger](http://www.rollerweblogger.org/){.externalLink}) has written
a very nice blog** **[How Rome
Works](http://www.rollerweblogger.org/page/roller/20040808#how_rome_works){.externalLink}**
**describing (as the title says) how Rome works. With his permission we
are adding it to Rome documentation.**

I spent some time exploring the new
[Rome](http://rome.dev.java.net/){.externalLink} feed parser for Java
and trying to understand how it works. Along the way, I put together the
following class diagram and notes on the parsing process. I provide some
pointers into the [Rome 0.4
Javadocs](http://rome.dev.java.net/apidocs/0_4/overview-summary.html){.externalLink}.

You don\'t need to know this stuff to use Rome, but it you are
interested in internals you might find it interesting.

### Notes on the Rome parsing process

Rome is based around an idealized and abstract model of a Newsfeed or
\"Syndication Feed.\" Rome can parse any format of Newsfeed, including
RSS variants and Atom, into this model. Rome can convert from model
representation to any of the same Newfeed output formats.

Internally, Rome defines intermediate object models for specific
Newsfeed formats, or \"Wire Feed\" formats, including both Atom and all
RSS variants. For each format, there is a separate JDOM based parser
class that parses XML into an intermediate model. Rome provides
\"converters\" to convert between the intermediate Wire Feed models and
the idealized Syndication Feed model.

Rome makes no attempt at [Pilgrim-style liberal XML
parsing](http://www.xml.com/pub/a/2003/01/22/dive-into-xml.html){.externalLink}.
If a Newsfeed is not valid XML, then Rome will fail. Perhaps, as [Kevin
Burton
suggests](http://www.peerfear.org/rss/permalink/2003/01/23/1043368363-Smart_Parsing__Not_RSS_Parsing.shtml){.externalLink},
parsing errors in Newsfeeds can and should be corrected. Kevin suggests
that, when the parse fails, you can correct the problem and parse again.
(BTW, I have some sample code that shows how to do this, but it only
works with Xerces - Crimsom\'s SAXParserException does not have reliable
error line and column numbers.)

Here is what happens during Rome Newsfeed parsing:

![](HowRomeWorks.png)

1.  Your code calls
    [SyndFeedInput](http://rome.dev.java.net/apidocs/0_4/com/sun/syndication/io/SyndFeedInput.html){.externalLink}
    to parse a Newsfeed, for example (see also [Using Rome to read a
    syndication
    feed](./RomeV0.4TutorialUsingRomeToReadASyndicationFeed.html)):

    ```java
        URL feedUrl = new URL("file:blogging-roller.rss");
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new InputStreamReader(feedUrl.openStream()));
    ```
2.  SyndFeedInput delegates to WireFeedInput to do the actual parsing.
3.  [WireFeedInput](http://rome.dev.java.net/apidocs/0_4/com/sun/syndication/io/WireFeedInput.html){.externalLink}
    uses a PluginManager of class FeedParsers to pick the right parser
    to use to parse the feed and then calls that parser to parse the
    Newsfeed.
4.  The appropriate parser parses the Newsfeed parses the feed, using
    [JDom](http://www.jdom.org/){.externalLink}, into a
    [WireFeed](http://rome.dev.java.net/apidocs/0_4/com/sun/syndication/feed/WireFeed.html){.externalLink}.
    If the Newsfeed is in an RSS format, the the WireFeed is of class
    [Channel](http://rome.dev.java.net/apidocs/0_4/com/sun/syndication/feed/rss/Channel.html){.externalLink}
    and contains Items, Clouds, and other RSS things from the
    [com.rometools.rome.feed.rss](http://rome.dev.java.net/apidocs/0_4/com/sun/syndication/feed/rss/package-summary.html){.externalLink}
    package. Or, on the other hand, if the Newsfeed is in Atom format,
    then the WireFeed is of class
    [Feed](http://rome.dev.java.net/apidocs/0_4/com/sun/syndication/feed/atom/Feed.html){.externalLink}
    from the
    [com.rometools.rome.atom](http://rome.dev.java.net/apidocs/0_4/com/sun/syndication/feed/atom/package-summary.html){.externalLink}
    package. In the end, WireFeedInput returns a WireFeed.
5.  SyndFeedInput uses the returned WireFeedInput to create a
    SyndFeedImpl. Which implements SyndFeed. SyndFeed is an interface,
    the root of an abstraction that represents a format independent
    Newsfeed.
6.  [SyndFeedImpl](http://rome.dev.java.net/apidocs/0_4/com/sun/syndication/feed/synd/SyndFeed.html){.externalLink}
    uses a Converter to convert between the format specific WireFeed
    representation and a format-independent SyndFeed.
7.  SyndFeedInput returns to you a SyndFeed containing the parsed
    Newsfeed.
:::

::: section
### Other Rome features

Rome supports Newsfeed extension modules for all formats that also
support modules: RSS 1.0, RSS 2.0, and Atom. Standard modules such as
Dublic Core and Syndication are supported and you can [define your own
custom
modules](./RomeV0.4TutorialDefiningACustomModuleBeanParserAndGenerator.html)
too.

Rome also supports [Newsfeed
output](./RomeV0.4TutorialUsingRomeToCreateAndWriteASyndicationFeed.html)
and for each Newsfeed format provides a \"generator\" class that can
take a Syndication Feed model and produce from it Newsfeed XML.
:::

::: section
### Learning more

I\'ve linked to a number of the Rome 0.4 Tutorials, here is the full
list from the [Rome Wiki](../index.html):

1.  [Using Rome to read a syndication
    feed](./RomeV0.4TutorialUsingRomeToReadASyndicationFeed.html)
2.  [Using Rome to convert a syndication feed from one type to
    another](./RomeV0.4TutorialUsingRomeToConvertASyndicationFeedFromOneTypeToAnother.html)
3.  [Using Rome to aggregate many syndication feeds into a single
    one](./RomeV0.4TutorialUsingRomeToAggregateManySyndicationFeedsIntoASingleOne.html)
4.  [Using Rome to create and write a
    feed](./RomeV0.4TutorialUsingRomeToCreateAndWriteASyndicationFeed.html)
5.  [Defining a Custom Module bean, parser and
    generator](./RomeV0.4TutorialDefiningACustomModuleBeanParserAndGenerator.html)
:::

::: section
### Conclusion

Overall, Rome looks really good. It is obvious that a lot of thought has
gone into design and a lot of work has been done on implementation (and
docs). Rome is well on the way to \"ending syndication feed confusion by
supporting all of \'em\" for us Java heads.
:::
:::
