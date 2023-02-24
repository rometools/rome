::: section
## Sample programs included

There are two sample programs included with Rome Fetcher.

[FeedReader](https://github.com/rometools/rome-fetcher/tree/master/src/main/java/org/rometools/fetcher/samples/FeedReader.java){.externalLink}
is a program which demonstrates the use of the Fetcher to retrieve a
feed and then to use the conditional get support to retrieve it again
only if it has changed. It also shows how to use the event API in the
Fetcher. It can be run using the `maven run-read` target.

[FeedAggregator](https://github.com/rometools/rome-fetcher/tree/master/src/main/java/org/rometools/fetcher/samples/FeedAggregator.java){.externalLink}
is a program which aggregates a number of feeds together into a single
feed. It can be run using the `maven run-aggr` target.
:::
