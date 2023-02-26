## Rome Fetcher

The Rome Fetcher (see modules/fetcher) allows the retrieval of feeds via HTTP. 
It supports [HTTP conditional gets](http://fishbowl.pastiche.org/2002/10/21/http_conditional_get_for_rss_hackers)
(ie: last modified and ETag handling) and GZip encoded feeds. It should enable 
user to write aggregators that follow the [Atom aggregator behaviour recommendations (Archived)](https://web.archive.org/web/20060712072932/http://diveintomark.org/archives/2003/07/21/atom_aggregator_behavior_http_level)

As with the rest of Rome, the Fetcher subproject is ultra-lean - it requires no 
new dependencies over the requirements for Rome.
