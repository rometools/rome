## Technical Documentation

Rome is based around an idealized and abstract model of a Newsfeed or 
"Syndication Feed". Rome can parse any format of Newsfeed, including RSS 
variants and Atom, into this model. Rome can convert from model representation 
to any of the same Newfeed output formats.

Internally, Rome defines intermediate object models for specific Newsfeed 
formats, or "Wire Feed" formats, including both Atom and all RSS variants. For 
each format, there is a separate parser class that parses XML into an 
intermediate model. Rome provides "converters" to convert between the 
intermediate Wire Feed models and the idealized Syndication Feed model.

Here is what happens during Rome Newsfeed parsing:

![](overview.png)

1.  Your code calls *SyndFeedInput* to parse a Newsfeed.
2.  SyndFeedInput delegates to WireFeedInput to do the actual parsing.
3.  WireFeedInput uses a PluginManager of class FeedParsers to pick the right 
    parser to use to parse the feed and then calls that parser to parse the
    Newsfeed.
4.  The appropriate parser parses the Newsfeed into a *WireFeed*. If the 
    Newsfeed is in an RSS format, the WireFeed is of class *Channel*. If the 
    Newsfeed is in Atom format, then the WireFeed is of class *Feed*. In the 
    end, WireFeedInput returns a WireFeed.
5.  SyndFeedInput uses the returned WireFeedInput to create a SyndFeedImpl. 
    Which implements SyndFeed. SyndFeed is an interface, the root of an 
    abstraction that represents a format independent Newsfeed.
6.  *SyndFeedImpl* uses a Converter to convert between the format specific 
    WireFeed representation and a format-independent SyndFeed.
7.  SyndFeedInput returns a SyndFeed containing the parsed Newsfeed.

### Other Rome features

Rome supports Newsfeed extension modules for all formats that also support 
modules: RSS 1.0, RSS 2.0, and Atom. Standard modules such as Dublic Core and 
Syndication are supported and you can define your own custom modules too.

Rome also supports the generation of Newsfeeds and provides a generator class
for each Newsfeed format that can take a Syndication Feed model and produce from
it Newsfeed XML.
