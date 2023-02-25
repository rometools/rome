## Welcome to Rome

Rome (**R**SS and At**om** utiliti**e**s) is a Java library for RSS and Atom 
feeds. It's open source and licensed under the Apache 2.0 license.

Rome includes a set of parsers and generators for the various flavors of 
syndication feeds, as well as converters to convert from one format to another. 
The parsers can give you back Java objects that are either specific for the 
format you want to work with, or a generic normalized SyndFeed class that lets 
you work on with the data without bothering about the incoming or outgoing feed 
type.

### Modules

| Module | Description |
| --- | --- |
| [Certiorem](./Certiorem/index.html) (deprecated) | PubSubHubub protocol for Rome. |
| [Fetcher](./Fetcher/index.html) (deprecated) |A caching feed fetcher that supports retrieval of feeds via [HTTP conditional GET](http://fishbowl.pastiche.org/2002/10/21/http_conditional_get_for_rss_hackers). Supports ETags, GZip compression, and [RFC3229 Delta encoding](https://en.wikipedia.org/wiki/Delta_encoding#Delta_encoding_in_HTTP). |
| [Modules](./Modules/index.html) | Provide support for feed extensions such as GeoRSS, iTunes, Microsoft SSE and SLE, Google GData and others. |
| [OPML](./Opml/index.html) | Outline Processor Markup Language (OPML) parser and tools. |
| [Propono](./Propono/index.html) (deprecated) | Supports publishing protocols, specifically the Atom Publishing Protocol and the legacy MetaWeblog API. Propono includes an Atom client library, an Atom server framework and a Blog client that supports both Atom protocol and the MetaWeblog API. |