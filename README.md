# Rome

[![Build Status](https://travis-ci.org/rometools/rome.svg?branch=master)](https://travis-ci.org/rometools/rome)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.rometools/rome/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.rometools/rome)

Rome is a Java framework for RSS and Atom feeds. The framework consist of several modules:

> &#x26a0;&#xfe0f; This project is in maintenance mode due to a shortage of active developers. We won't accept new features but try to keep all dependencies up-to-date.

## Project structure

| Module | Description |
| ------ | ----------- |
| `rome` | Library for generating and parsing RSS and Atom feeds. |
| `rome-modules` | Generators and parsers for extensions like MediaRSS, GeoRSS and others. |
| `rome-opml` | [OPML](https://en.wikipedia.org/wiki/OPML) parsers and tools. |
| `rome-fetcher` | DEPRECATED (see [#276](https://github.com/rometools/rome/issues/276) for details) |

Other deprecated modules: `rome-certiorem`, `rome-certiorem-webapp` and `rome-propono`.

## Examples

Parse a feed:

```java
String url = "https://stackoverflow.com/feeds/tag?tagnames=rome";
SyndFeed feed = new SyndFeedInput().build(new XmlReader(new URL(url)));
System.out.println(feed.getTitle());
```
**Beware!** The `URL` class used in this example is rudimentary and works only for simplest cases. Please consider using a separate library for fetching the feed (see example in [#276](https://github.com/rometools/rome/issues/276)).

Generate a feed:

```java
SyndFeed feed = new SyndFeedImpl();
feed.setFeedType("rss_2.0");
feed.setTitle("test-title");
feed.setDescription("test-description");
feed.setLink("https://example.org");
System.out.println(new SyndFeedOutput().outputString(feed));
```
