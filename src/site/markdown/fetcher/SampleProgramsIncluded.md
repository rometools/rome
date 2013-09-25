# Sample programs included


There are two sample programs included with Rome Fetcher.



[FeedReader](https://rometools.jira.com/source/browse/FETCHER/trunk/src/java/com/sun/syndication/fetcher/samples/FeedReader.java) is a program which demonstrates the use of the Fetcher to retrieve a feed and then to use the conditional get support to retrieve it again only if it has changed. It also shows how to use the event API in the Fetcher. It can be run using the `maven run\-read` target.



[FeedAggregator](https://rometools.jira.com/source/browse/FETCHER/trunk/src/java/com/sun/syndication/fetcher/samples/FeedAggregator.java) is a program which aggregates a number of feeds together into a single feed. It can be run using the `maven run\-aggr` target.



(Note that in verision 0.3 the Maven build does not include Xerces in the classpath \- which is required to use Rome. This is [easy to fix](https://rome.dev.java.net/source/browse/rome/subprojects/fetcher/project.xml?r1=1.1&amp;r2=1.2).)

