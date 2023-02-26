## Best Practice

Rome allows developers to work with classes that closely resemble a particular
syndication feed type. For example, the Channel class for RSS feeds and the Feed
class for Atom feeds. All the Synd* classes, which leverage the RSS and Atom 
specific classes, are the bridge to go from one syndication type to another.

For day to day coding, we found ourselves using the **Synd\*** classes as we 
need to do applications that understand the different syndication feed types. 
And it is much simpler to work with a higher and independent abstraction.
