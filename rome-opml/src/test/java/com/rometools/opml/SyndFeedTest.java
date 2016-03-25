package com.rometools.opml;

/**
 * @author pat
 *
 */
public abstract class SyndFeedTest extends FeedTest {
    private String _prefix = null;

    protected SyndFeedTest(final String feedType) {
        this(feedType, feedType + ".xml");
    }

    protected SyndFeedTest(final String feedType, final String feedFileName) {
        super(feedFileName);
        _prefix = feedType;
    }

    protected String getPrefix() {
        return _prefix;
    }

    protected void assertProperty(final String property, final String value) {
        assertEquals(property, getPrefix() + "." + value);
    }

    public void testType() throws Exception {
        assertEquals(getCachedSyndFeed().getFeedType(), getPrefix());
    }

    /*
     * public void testType() throws Exception { assertEquals(getPrefix(),
     * getCachedSyndFeed().getFeedType()); } public void testTitle() throws Exception {
     * assertEqualsStr("channel.title", getCachedSyndFeed().getTitle()); } public void testLink()
     * throws Exception { assertEqualsStr("channel.link", getCachedSyndFeed().getLink()); } public
     * void testDescription() throws Exception { assertEqualsStr("channel.description",
     * getCachedSyndFeed().getDescription()); } public void testLanguage() throws Exception {
     * assertEqualsStr("channel.language", getCachedSyndFeed().getLanguage()); } public void
     * testCategories() throws Exception { List catlist = getCachedSyndFeed().getCategories();
     * //don't understand why this one fails assertEquals(2, catlist.size()); SyndCategory cat =
     * (SyndCategory)catlist.get(0); assertEqualsStr("channel.category[0]", cat.getName());
     * assertEqualsStr("channel.category[0]^domain", cat.getTaxonomyUri()); cat =
     * (SyndCategory)catlist.get(1); assertEqualsStr("channel.category[1]", cat.getName());
     * assertEqualsStr("channel.category[1]^domain", cat.getTaxonomyUri()); } public void
     * testPublishedDate() throws Exception {
     * assertEquals(DateParser.parseRFC822("Mon, 01 Jan 2001 00:00:00 GMT"),
     * getCachedSyndFeed().getPublishedDate()); } //how do i get height and width? public void
     * testImage() throws Exception { SyndImage img = getCachedSyndFeed().getImage();
     * assertEqualsStr("channel.image.description", img.getDescription());
     * assertEqualsStr("channel.image.link", img.getLink()); assertEqualsStr("channel.image.title",
     * img.getTitle()); assertEqualsStr("channel.image.url", img.getUrl()); } public void
     * testEntries() throws Exception { List entrylist = getCachedSyndFeed().getEntries();
     * assertEquals(2, entrylist.size()); } public void testEntryTitle() throws Exception {
     * assertEqualsStr("channel.item[0].title",
     * getEntryTitle(getCachedSyndFeed().getEntries().get(0)));
     * assertEqualsStr("channel.item[1].title",
     * getEntryTitle(getCachedSyndFeed().getEntries().get(1))); } public String getEntryTitle(Object
     * o) throws Exception { SyndEntry e = (SyndEntry) o; return e.getTitle(); } public void
     * testEntryDescription() throws Exception { assertEqualsStr("channel.item[0].description",
     * getEntryDescription(getCachedSyndFeed().getEntries().get(0)));
     * assertEqualsStr("channel.item[1].description",
     * getEntryDescription(getCachedSyndFeed().getEntries().get(1))); } public String
     * getEntryDescription(Object o) throws Exception { SyndEntry e = (SyndEntry) o; return
     * e.getDescription().getValue(); } public void testEntryLink() throws Exception {
     * assertEqualsStr("channel.item[0].link",
     * getEntryLink(getCachedSyndFeed().getEntries().get(0)));
     * assertEqualsStr("channel.item[1].link",
     * getEntryLink(getCachedSyndFeed().getEntries().get(1))); } public String getEntryLink(Object
     * o) { SyndEntry e = (SyndEntry) o; return e.getLink(); } public void testEntryPublishedDate()
     * throws Exception { assertEquals(DateParser.parseRFC822("Mon, 01 Jan 2001 00:00:00 GMT"),
     * getEntryPublishedDate(getCachedSyndFeed().getEntries().get(0)));
     * assertEquals(DateParser.parseRFC822("Mon, 01 Jan 2001 00:00:00 GMT"),
     * getEntryPublishedDate(getCachedSyndFeed().getEntries().get(1))); } public Date
     * getEntryPublishedDate(Object o) { SyndEntry e = (SyndEntry) o; return e.getPublishedDate(); }
     * public void testEntryCategories() throws Exception { SyndEntry e =
     * (SyndEntry)getCachedSyndFeed().getEntries().get(0); List catlist = e.getCategories(); //don't
     * understand why this one fails assertEquals(2, catlist.size()); SyndCategory cat =
     * (SyndCategory)catlist.get(0); assertEqualsStr("channel.item[0].category[0]", cat.getName());
     * assertEqualsStr("channel.item[0].category[0]^domain", cat.getTaxonomyUri()); cat =
     * (SyndCategory)catlist.get(1); assertEqualsStr("channel.item[0].category[1]", cat.getName());
     * assertEqualsStr("channel.item[0].category[1]^domain", cat.getTaxonomyUri()); //DO 2nd set of
     * items } public void testEntryAuthor() throws Exception {
     * assertEqualsStr("channel.item[0].author",
     * getEntryAuthor(getCachedSyndFeed().getEntries().get(0)));
     * assertEqualsStr("channel.item[1].author",
     * getEntryAuthor(getCachedSyndFeed().getEntries().get(1))); } public String
     * getEntryAuthor(Object o) { SyndEntry e = (SyndEntry) o; return e.getAuthor(); }
     */
    /*
     * //things you cannot get from SyndEntryImpl // <source
     * url="http://localhost:8080/item0/source.url">item[0].source</source> // <enclosure
     * url="http://localhost:8080/item0/enclosure0.url" length="100" type="audio/mpeg"/> //
     * <enclosure url="http://localhost:8080/item0/enclosure1.url" length="1000" type="audio/mpeg"/>
     * <category domain="item0.domain0">item0.category0</category> <category
     * domain="item0.domain1">item0.category1</category> <pubDate>Thu, 08 Jul 1999 08:00:00
     * GMT</pubDate> <expirationDate>Thu, 08 Jul 1999 09:00:00 GMT</.expirationDate>
     * <author>item0.author</author> <comments>http://localhost:8080/item0/comments</comments> <guid
     * isPermaLink="true">http://localhost:8080/item0/guid</guid> //TODO: I still have the elements
     * to test
     */
    /*
     * public void test() { assertEqualsStr(feed, ""); } public void test() { assertEqualsStr(feed,
     * ""); }
     */
    // Things that you cannot get form a SyndFeedImpl today
    // these need to be put in a RSS 2.0 module
    // or is a roundtrip to wirefeed the right way to do this?
    /*
     * <textInput> <title>Search</title> <description>Search this site:</description> <name>q</name>
     * <link>http://example.org/mt/mt-search.cgi</link> </textInput> image height and width
     * //<copyright>Copyright 2004, Mark Pilgrim</copyright> public void test() {
     * assertEqualsStr(getCachedSyndFeed()., ""); } //<generator>Sample Toolkit</generator> public
     * void test() { assertEqualsStr(feed, ""); } //
     * <managingEditor>editor@example.org</managingEditor> public void test() {
     * assertEqualsStr(feed, ""); } // <webMaster>webmaster@example.org</webMaster> public void
     * test() { assertEqualsStr(feed, ""); } <docs>http://blogs.law.harvard.edu/tech/rss</docs>
     * <cloud domain="rpc.sys.com" port="80" path="/RPC2" registerProcedure="pingMe"
     * protocol="soap"/> <ttl>60</ttl> <rating></rating> <skiphours> <hour>0</hour> <hour>1</hour>
     * <hour>2</hour> <hour>3</hour> <hour>4</hour> <hour>5</hour> <hour>6</hour> <hour>7</hour>
     * <hour>8</hour> <hour>9.5</hour> <hour>10</hour> <hour>11</hour> <hour>12</hour>
     * <hour>13</hour> <hour>14</hour> <hour>15</hour> <hour>16</hour> <hour>17</hour>
     * <hour>18</hour> <hour>19</hour> <hour>20</hour> <hour>21</hour> <hour>22</hour>
     * <hour>23</hour> </skiphours> <skipdays> <day>Monday</day> <day>Tuesday</day>
     * <day>Wednesday</day> <day>Thursday</day> <day>Friday</day> <day>Saturday</day>
     * <day>Sunday</day> </skipdays>
     */

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

}