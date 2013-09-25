# Known Issues

## Version 0.3


 
* The Maven build does not run the jetty tests because of a bug in Maven
 
* Version 0.3 does not have Xerces included in the project.xml (it is required to run the samples). Either get the latest project.xml from CVS, or [patch it yourself](https://rome.dev.java.net/source/browse/rome/subprojects/fetcher/project.xml?r1=1.1&amp;r2=1.2)
 
* 0.3 had a bug that caused it to overwite system properties.
 

## Version 0.4


 
* No known issues (yet!)
 

## Version 0.5


 
* When listening to feed events using FetcherListener, there is no way to get to the retrieved content, because it is set after firing the event. \-\- [jawe](http://wiki.java.net/twiki/bin/view/Javawsxml/Jawe)
 
* When listening to feed events using FetcherListener, the feed URLs returned by the FetcherEvent are prepended with "sun.net.www.protocol.http.HttpURLConnection:" \-\- [jawe](http://wiki.java.net/twiki/bin/view/Javawsxml/Jawe)
 

## Version 0.7


 
* HashMapFeedInfoCache doesn't work quite right because URL.hashCode() does hostname resolution and treats virtual hosts with the same IP as equal, so e.g. all RSS feeds from blogspot.com collide in the cache. Also, it's really slow. Fix is to use URL.toExternalForm() as the hash key instead of the URL itself.
 
