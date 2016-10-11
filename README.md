# ROME

[![Build Status](https://travis-ci.org/rometools/rome.svg?branch=master)](https://travis-ci.org/rometools/rome)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.rometools/rome/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.rometools/rome)

ROME is a Java framework for RSS and Atom feeds. The framework consist of several modules:

| Module | Description |
| ------ | ----------- |
| `rome` | Library for generating and parsing RSS and Atom feeds. |
| `rome-modules` | Generators and parsers for extensions like MediaRSS, GeoRSS and others. |
| `rome-opml` | [OPML](https://en.wikipedia.org/wiki/OPML) parsers and tools. |

Deprecated modules: `rome-fetcher`, `rome-certiorem`, `rome-certiorem-webapp` and `rome-propono`.

## Examples

Parse a feed:

```java
String url = "http://stackoverflow.com/feeds/tag?tagnames=rome";
SyndFeed feed = new SyndFeedInput().build(new XmlReader(new URL(url)));
System.out.println(feed.getTitle());
```

Generate a feed:

```java
SyndFeed feed = new SyndFeedImpl();
feed.setFeedType("rss_2.0");
feed.setTitle("test-title");
feed.setDescription("test-description");
feed.setLink("https://example.org");
System.out.println(new SyndFeedOutput().outputString(feed));
```
