# Feeds Date Elements Mapping to SyndFeed and SyndEntry


The different RSS versions and Atom define different date elements at feed and entry level. Some of them also define syndication information indicating when (and when not) and how often to fetch feeds for updates. There is not always a possible mapping or conversion of this information when going from one format to another.



As this is still subject of debate ([How About a Date](http://www.tbray.org/ongoing/When/200x/2004/07/30/Dates) and [Date Survey](http://www.intertwingly.net/wiki/pie/DateSurvey)), for now, in Rss and atOM utilitiEs (ROME) we've taken a simplistic approach.



When handling feeds at WireFeed level, **_rss.Channel_** or **_atom.Feed_**, it is possible to access all the date elements and syndication information available in the feed.



When handling feeds at SyndFeed level, **_synd.SyndFeed_**, there is only one date element available, the **_publishedDate_**. Both, **_SyndFeed_** and **_SyndEntry_** have the **_publisheDate_** property. In addition, it is possible to use the Syndication Module.



The mapping of the date elements from the different feed formats to SyndFeed is as follows.


## For RSS 0.90



RSS 0.90 does not define date elements.



There is no mapping to **_SyndFeed_** and **_SyndEntry_** date properties.


## For RSS 0.91, 0.92



RSS 0.91 and 0.92 define **_pubDate_** and **_lastBuildDate_** at feed level.



The feed **_pubDate_** element is mapped to the **_SyndFeed_** **_publishedDate_** property. The **_lastBuildDate_** element is lost.


## For RSS 0.93, 0.94 and 2.0



RSS 0.93, 0.94 and 2.0 define **_pubDate_** and **_lastBuildDate_** at feed level. They also define **_pubDate_** and **_expirationDate_** at item level.



The feed **_pubDate_** element is mapped to the **_SyndFeed_** **_publishedDate_** property. The **_lastBuildDate_** element is lost.



The item **_pubDate_** element is mapped to the **_SyndEntry_** **_publishedDate_** property. The **_expirationDate_** element is lost.


## For RSS 1.0



RSS 1.0 use DC Module data at feed an item level to indicate date information about the feed and the items.



**_SyndFeed_** and **_SyndEntry_** use the DC Module **_date_** element for the **_publishedDate_** property.


## For Atom 0.3



Atom 0.3 defines a **_modified_** element at feed level and the **_modified_**, **_created_** & **_issued_** elements at entry level.



The feed **_modified_** element is mapped to the **_SyndFeed_** **_publishedDate_** property.



The item **_modified_** element is mapped to the **_SyndEntry_** **_publishedDate_** property. The entry elements, **_created_** and **_issued_**, are lost.


## For Atom 1.0



(Atom 1.0 supported in ROME since v0.8)



Atom 1.0 defines an **_updated_** element at the feed level, which ROME maps to **_SyndFeed.publishedDate_**.



Atom 1.0 defines **_updated_** and **_published_** elements at the entry level, which ROME maps to **_SyndEntry.updatedDate_** and **_SyndEntry.publishedDate_** respectively.

