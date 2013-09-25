# Why this project?


Various flavors of RSS and Atom syndication formats were reaching a tipping point in 2004. At Sun we started various projects involving these Syndication formats, but when looking around for Java libraries to take care of the parsing and generation of RSS we were not satisfied with what we found. [Project ROME](https://rometools.jira.com/) was started out of this frustration.



Our requirements are to **ESCAPE** from [Syndication Feeds Hell](http://blogs.sun.com/tucu/entry/syndication_feeds_hell). In order to allow that the library must be:


 
* \*E\*asy to use: given a url, get back a feed object independent of the underlying format, and serialize the feed object to the format I want.
 
* \*S\*imple: RSS initially stood for "Really Simple Syndication"\*, and this simplicity is what made the format successful. Specifications wars have made the current situation much more complicated. The goal of the library is to give that simplicity back to developers: each API we use force on us a mental model of the domain and we are using more and more libraries on each project we implement. This library tries to ease the cognitive load of developers and provides a very simple model for feeds and entries, abstarcting out the details of the various underlying formats.
 
* \*C\*omplete: must handle all versions of RSS and Atom
 
* \*A\*bstract: provides a Java\-friendly abstraction layer on top of the various syndication specifications, that maps the commonalities of the various feed formats into a single simple JavaBeans Data Model.
 
* \*P\*owerful: lets me access all the metadata of the feeds regardless of their format. If I need them, lets me access optional metadata expressed in extensions accepted by various formats (RSS 1.0 modules, other namespaces in Atom).
 
* \*E\*xtensible: It needs to define a simple pluggable architecture to provide support for future extensions of the formats.
 


\* \- not so, it was originally "RDF Site Summary", see: [RSS History](http://goatee.net/2003/rss-history.html)



We set out to create this library in the same spirit as the [JDOM](http://www.jdom.org/mission/index.html) library for XML manipulation in Java, incorporating XOM's [Elliotte Rusty Harold's pearls of wisdom about API design and refactoring](http://www.artima.com/intv/jdom.html) (see [Air Bags and Other Design Principles](http://www.artima.com/intv/airbags.html) which links his 6 interviews with Bill Venners). ROME implementation uses JDOM.

