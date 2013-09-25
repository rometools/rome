## Welcome to ROME

_"...ending syndication feed confusion by supporting all of 'em. "_ _[\*](http://blogs.sun.com/roller/page/webmink/20040616#rome_wasn_t_built_in)_

**ROME is a set of _R_SS and At_om_ Utiliti_e_s for Java that is open source under the Apache 2.0 license.**

RSS 0.90, RSS 0.91 Netscape, RSS 0.91 Userland, RSS 0.92, RSS 0.93, RSS 0.94, RSS 1.0, RSS 2.0, Atom 0.3, Atom 1.0

ROME includes a set of parsers and generators for the various flavors of syndication feeds, as well as converters to convert from one format to another.
The parsers can give you back Java objects that are either specific for the format you want to work with, or a generic normalized SyndFeed class that
lets you work on with the data without bothering about the incoming or outgoing feed type.

If you use ROME for your site or software, please add it to the wiki page [PoweredByRome](ProductsOrSitesPoweredByROME.html), or drop us an email

Some of the links in the Navigation are out of date. This is because no one can edit them at present. However any registered user can edit the content
of the pages and you'll find most of the links updated there, notably the Tutorials and Articles.

## ROME Subprojects

<table>
	<tbody>
		<tr>
			<th>Subproject</th>
			<th>Purpose</th>
			<th>Latest Release</th>
		</tr>
		<tr>
			<td><a href="../fetcher/index.html">ROME Fetcher</a></td>
			<td>A caching feed fetcher that supports retrieval of feeds via <a href="http://fishbowl.pastiche.org/2002/10/21/http_conditional_get_for_rss_hackers">HTTP conditional GET</a>.
			 Supports ETags, GZip compression, and <a href="http://bobwyman.pubsub.com/main/2004/09/using_rfc3229_w.html">RFC3229 Delta encoding</a>.</td>
			<td><a href="../fetcher/Releases/ROMEFetcher1.0.html">ROME Fetcher</a> v1.0 (Mar 11 2009)</td>
		</tr>
		<tr>
			<td><a href="../modules/index.html">Rome Modules</a></td>
			<td>Provide support for feed extensions such as GeoRSS, iTunes, Microsoft SSE and SLE, Google GData and others.</td>
			<td><a href="../modules/index.html">ROME Modules 1.0</a> (Feb 24 2011)</td>
		</tr>
		<tr>
			<td><a href="../propono/index.html">ROME Propono</a></td>
			<td>Supports publishing protocols, specifically the Atom Publishing Protocol and the legacy MetaWeblog API. Propono includes an Atom client library,
			 an Atom server framework and a Blog client that supports both Atom protocol and the MetaWeblog API.</td>
			<td><a href="../propono/ROMEProponoVersion0.6.html">ROME Propono v0.6</a>.</td>
		</tr>
		<tr>
			<td><a href="../mano/index.html">ROME Mano</a></td>
			<td>A servlet pipeline framework for RSS and Atom feeds.</td>
			<td></td>
		</tr>
		<tr>
			<td><a href="../opml/index.html">OPML for ROME</a></td>
			<td>Outline Processor Markup Language (OPML) parser and tools.</td>
			<td></td>
		</tr>
	</tbody>
</table>

## Further information

* [ROME Releases](ROMEReleases/index.html)
* Working with ROME
    * [What part of the API you should be using](WhatPartOfTheAPIYouShouldBeUsing.html)
    * [Tutorials and Articles](TutorialsAndArticles.html)
* Articles about ROME
    * [Project Motivation](WhyThisProject.html) \- why we started ROME
    * [API FAQ](RomeAPIFAQ.html), why things are like they are
    * [Evaluation of existing RSS parsing libraries](WhatSWrongWithOtherExistingRSSParsingLibraries.html)
* Inside ROME, How Things Work
    * [How ROME Works](HowRomeWorks/index.html), Understanding ROME, a detailed overview by Dave Johnson (This doc is based on ROME v0.4)
    * [ROME Plugins Mechanism](RssAndAtOMUtilitiEsROMEV0.5AndAboveTutorialsAndArticles/RssAndAtOMUtilitiEsROMEPluginsMechanism.html), bootstrap, adding and changing parsers, generators, converters and modules
    * [Feeds Date Elements](RssAndAtOMUtilitiEsROMEV0.5AndAboveTutorialsAndArticles/FeedsDateElementsMappingToSyndFeedAndSyndEntry.html), how Date data is mapped to SyndFeed and SyndEntry
    * [Feed and Entry URI Mapping](RssAndAtOMUtilitiEsROMEV0.5AndAboveTutorialsAndArticles/FeedAndEntryURIMappingHowSyndFeedAndSyndEntryUriPropertiesMapToRSSAndAtomElements.html), how SyndFeed and SyndEntry 'uri' properties map to concrete feed elements
    * [XML Charset Encoding Detection](RssAndAtOMUtilitiEsROMEV0.5AndAboveTutorialsAndArticles/XMLCharsetEncodingDetectionHowRssAndAtOMUtilitiEsROMEHelpsGettingTheRightCharsetEncoding.html), how ROME helps getting the right charset encoding
    * [Creating a custom Module](RssAndAtOMUtilitiEsROMEV0.5AndAboveTutorialsAndArticles/RssAndAtOMUtilitiEsROMEV0.5TutorialDefiningACustomModuleBeanParserAndGenerator.html), creating all necessary pieces, bean, parser and generator
    * [The CopyFrom interface](RssAndAtOMUtilitiEsROMEV0.5AndAboveTutorialsAndArticles/TheCopyFromInterface.html)
    * [ROME bean utilities, equals, toString and cloning](RssAndAtOMUtilitiEsROMEV0.5AndAboveTutorialsAndArticles/UnderstandingRssAndAtOMUtilitiEsROMEBeanUtilities.html)
    * [Customizing Date and time parsing](RssAndAtOMUtiliEsROMEV0.7DateAndTimeParsing.html) in the rome.properties file
    * [Using ROME from Maven2](ROMEAndMaven2.html)
    * [Using ROME with OSGi](ROMEAndOSGI.html)
    * [Preserving Wire Feeds to obtain access to Atom/RSS specific fields](PreservingWireFeeds.html)
* ROME development
    * [How to build ROME](HowToBuildRome.html) (ROME uses [Maven](http://maven.apache.org/))
    * [Changes Log](ChangeLog.html), what and when
    * [ROME Development Process](ROMEDevelopmentProcess.html)
    * [ROME Development Proposals](ROMEDevelopmentProposals/index.html) \- proposals for new ROME features and releases
 