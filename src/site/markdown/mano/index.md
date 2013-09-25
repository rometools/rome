# Home
  
# ROME.Mano v0.0 pre\-Alpha

 

Mano (_to flow_ in Latin) is a pipeline framework for RSS and Atom feeds.

 
 
* [The Concept](http://wiki.java.net/bin/view/Javawsxml/RomeMano?skin=print#The_Concept)
 
* [Being More Concrete](http://wiki.java.net/bin/view/Javawsxml/RomeMano?skin=print#Being_More_Concrete)
 
* [What Is in the Box?](http://wiki.java.net/bin/view/Javawsxml/RomeMano?skin=print#What_Is_in_the_Box) 
 
    * [The FeedHandler Public API](http://wiki.java.net/bin/view/Javawsxml/RomeMano?skin=print#The_FeedHandler_Public_API)
 
    * [The Mano Servlet](http://wiki.java.net/bin/view/Javawsxml/RomeMano?skin=print#The_Mano_Servlet)
 
    * [The FeedHandler Deployment Descriptor](http://wiki.java.net/bin/view/Javawsxml/RomeMano?skin=print#The_FeedHandler_Deployment_Descr)
 
    * [The Handler Toolbox](http://wiki.java.net/bin/view/Javawsxml/RomeMano?skin=print#The_Handler_Toolbox) 
 
    * [Fetch Handler](http://wiki.java.net/bin/view/Javawsxml/RomeMano?skin=print#Fetch_Handler)
 
    * [Convert Handler](http://wiki.java.net/bin/view/Javawsxml/RomeMano?skin=print#Convert_Handler)
 
    * [Branding Handler](http://wiki.java.net/bin/view/Javawsxml/RomeMano?skin=print#Branding_Handler)
 
    * [File Handler](http://wiki.java.net/bin/view/Javawsxml/RomeMano?skin=print#File_Handler)
 
    * [Clone Handler](http://wiki.java.net/bin/view/Javawsxml/RomeMano?skin=print#Clone_Handler)
 
    * [Paginate Handler](http://wiki.java.net/bin/view/Javawsxml/RomeMano?skin=print#Paginate_Handler)
 
    * [Multi Handler](http://wiki.java.net/bin/view/Javawsxml/RomeMano?skin=print#Multi_Handler)
 
    * [Tracking Handler & Servlet](http://wiki.java.net/bin/view/Javawsxml/RomeMano?skin=print#Tracking_Handler_Servlet)
 
    * [Echo Handler (alias the Uploader)](http://wiki.java.net/bin/view/Javawsxml/RomeMano?skin=print#Echo_Handler_alias_the_Uploader)
 
    * [Null Handler](http://wiki.java.net/bin/view/Javawsxml/RomeMano?skin=print#Null_Handler)
 
    * [Sort Handler](http://wiki.java.net/bin/view/Javawsxml/RomeMano?skin=print#Sort_Handler)
 
 
 
 
 
* [Building ROME.Mano](http://wiki.java.net/bin/view/Javawsxml/RomeMano?skin=print#Building_ROME_Mano)
 
* [Sample ROME.Mano URLs](http://wiki.java.net/bin/view/Javawsxml/RomeMano?skin=print#Sample_ROME_Mano_URLs)
 
 
## The Concept

 

Mano has been modeled after a widely accepted pattern, Java servlet filters.

 

A request for a feed flows out through a chain of handlers until a feed is synthesized then the feed flows back through the chain in reverse order.

 

As the request flows out handlers can modify the request, and when the request is flowing back with the feed the same handlers can them modify the feed.

 

```

|| Mano framework ||
|| ||
|| +\-------\-+ +\-------\-+ ||
feed URL  \-->  \|\|   \|         \|  \--> ...  \--> \|         \|    \|\|
|| | handler 1 | | handler N | ||
feed     <-\-   \|\|   \|         \|  <-\- ... <-\-  \|         \|    \|\|
|| +\-------\-+ +\-------\-+ ||
|| ||

```
 
## Being More Concrete

 

The Mano framework is a Java Web Application that has a servlet serving feeds.

 

Upon an HTTP request for a feed a Mano servlet based on the information in the request builds a handler sequence and invokes it, the response is a feed that is returned by the Mano servlet to the requester user\-agent.

 

A feed reader user\-agent requests a feed to a server running the Mano framework WebApp.

 

Based on information in the request the Mano servlet determines that the handlers to invoke are the convert and the fetch handlers and creates the corresponding handler chain.

 

```

 public class ManoFeedServlet extends HttpServlet {

  protected void doGet(HttpServletRequest req,HttpServletResponse res)
      throws ServletException, IOException {

    FeedHandlerRequest handlerReq = createRequest(req);
    FeedHandlerResponseImpl handlerRes = createResponse(req);
    List handlerList = createHandlerList(req);
    FeedHandlerChainImpl chain =
      new FeedHandlerChainImpl(handlerReq, handlerRes, handlerList);

    chain.execute();
    ...
  }

}

```
 

When flowing out the convert handler does nothing but chaining the request for the next handler (handlers do not know anything about earlier and later handlers in the chain, that is managed by the Mano framework).

 

```

 public class ConvertFeedHandler extends BaseFeedHandler {

  public void handle(FeedHandlerRequest handlerRequest,
      FeedHandlerResponse handlerResponse, FeedHandlerChain handlerChain)
      throws FeedHandlerException, IOException {

    handlerChain.chain(handlerRequest, handlerResponse);
    ...
  }

}

```
 

Then fetch handler receives the request, obtains the feed URL from a request parameter and using ROME Fetcher it fetches the feed. The resulting SyndFeed bean is set into the response by the fetch handler. The fetch handler is the end of the chain, it does not chain any further handlers.

 

```

 public class FetchFeedHandler extends BaseFeedHandler {

  public void handle(FeedHandlerRequest handlerRequest,
      FeedHandlerResponse handlerResponse, FeedHandlerChain handlerChain)
      throws FeedHandlerException, IOException {

    String url = handlerRequest.getParameter("fetch.url");
    SyndFeed feed = fetcher.retrieveFeed(new URL(url));
    handlerResponse.setSyndFeed(feed);
  }

}

```
 

At this point the Mano framework start the flow back of the request and the convert handler will regain control of the invocation, it will obtain the SyndFeed bean from the response and it will set the feed type of the bean to the type indicated in a request parameter.

 

```

 public class ConvertFeedHandler extends BaseFeedHandler {

  public void handle(FeedHandlerRequest handlerRequest,
      FeedHandlerResponse handlerResponse, FeedHandlerChain handlerChain)
      throws FeedHandlerException, IOException {

    handlerChain.chain(handlerRequest, handlerResponse);

    SyndFeed feed = handlerResponse.getSyndFeed();
    String toType = handlerRequest.getParameter("convert.to");
    feed.setFeedType(toType);
  }

}

```
 

The handler chain finishes, the control comes back to the Mano servlet, the Mano servlet gets the feed bean from the response (the handler response autoconverts from SyndFeed to WireFeed). The Mano servlet writes the feed bean to the servlet response.

 

```

 public class ManoFeedServlet extends HttpServlet {

  protected void doGet(HttpServletRequest req,HttpServletResponse res)
      throws ServletException, IOException {

    ...
    chain.execute();

    WireFeed feed = handlerRes.getWireFeed();
    WireFeedOutput feedOutput = new WireFeedOutput();
    feedOutput.output(feed, res.getWriter());
  }

}

```
 

The conversion from the original feed type to the feed type indicated in the convert.to parameter is done automatically by ROME when converting from SyndFeed to WireFeed just by setting the feed type in the SyndFeed bean.

 
## What Is in the Box?

 

ROME.Mano includes the handler API, a Mano servlet, a handler manager that uses a handler Deployment Descriptor and a toolbox with handlers (convert, fetch, branding, paginate, multi, clone and file).

 
### The FeedHandler Public API

 
 
* FeedHandler, methods: init(), handle() & destroy().
 
* FeedHandlerChain, method: chain().
 
* FeedHandlerConfig, methods: init parameters and context.
 
* FeedHandlerRequest, methods: to handle parameters, headers, attributes and principal.
 
* FeedHandlerResponse, methods: to set and retrieve WireFeed bean or SyndFeed bean, to set HTTP status, to set HTTP headers.
 
 
 
* BaseFeedHandler, convenience base class implementation of the FeedHandler.
 
* FeedHandlerException exception when something goes wrong.
 
 
### The Mano Servlet

 

The Mano Servlet must be mapped to a path ending with '/\*'. The handlers and their invocation order must be indicated in the extra path, handler names must be separated by a '/'.

 

Parameters in the query\-string are passed to the handlers, by convention the parameters should be namespaced with the name of the target handler. For example, for the **fetch** handler the **url** parameter should be named **fetch.url**.

 
### The FeedHandler Deployment Descriptor

 

The handler deployment descriptor, by default at /WEB\-INF/feedhandler.xml defines all the available handlers. A handler manager controls the lifecycle of the handlers behinds the scenes. For example:

 

```

 <feed-handlers>
  <handler>
    <name>fetch</name>
    <class>com.sun.syndication.handler.toolbox.FetchFeedHandler</class>
  </handler>
  <handler>
    <name>convert</name>
    <class>com.sun.syndication.handler.toolbox.ConvertFeedHandler</class>
    <init-parameter>
      <name>to.default</name>
      <value>rss_1.0</value>
    </init-parameter>
  </handler>
  ...
<feed-handlers>

```
 
### The Handler Toolbox

 

A collection of handlers is provided with the Mano framework.

 
#### Fetch Handler

 

It fetches a Feed URL.

 

It fetches the feed URL indicated in the namespaced **url** parameter. It uses ROME Fetcher \=HttpURLFeedFetcher to fetch the feed. This handler stops the handler chain.

 
#### Convert Handler

 

It converts a feed to the specified feed type.

 

After invoking the handler chain for further processing it sets the feed type to the value of the namespaced **to** parameter or to a default feed type if the parameter is not specified.

 
#### Branding Handler

 

It brands the description of the entries with the feed image (or the site favicon if there is not feed image) and the title.

 
#### File Handler

 

It reads or writes a feed from disk.

 

If the namespaced **read** parameter is present and there is a file with the value of the parameter (the value of the parameter does not have to be the URL of the feed, it can be any valid file name) the feed is read from the file and the handler stops the chain from continuing further out. If there is not file for the given ID then the handler lets the chain to continue further down.

 

If the namespaced **write** parameter is present after the filter chain returns the feed is written to a file with the value of the parameter as name.

 

If both **read** and **write** parameters are specified the handler acts a cache, if the read succeeds then read feed is returned, if the read fails then the chain continues and the obtained feed is written. A subsequent request will return the stored feed.

 
#### Clone Handler

 

It does a deep clone of the returned feed and sets the clone in the response.

 

Because feed beans are passed by reference between handlers, if a handler caches or stores feeds to be served to multiple users, it may be necessary to put a clone handler before it so earlier handlers modifying the feed do not affect the cached/stored feed.

 
#### Paginate Handler

 

It paginates the entries of a feed.

 

The namespaced **offset** (base 0)and **size** parameters indicate the range of entries to return. If **offset** is not present 0 is used. If **size** is not present a default value of 50 is used.

 
#### Multi Handler

 

It invokes the handler chain multiple times and consolidates the entries of all invocations into a single feed.

 

It is useful for merging feeds. For example, fetching N feeds and returning a single feed with all their entries.

 

The namespaced **param** indicates the name of the multivalue parameter to iterate for the multiple invocations. Each one of the multiple invocations sets the parameter indicated in the **param** parameter to a single value corresponding to the current iteration. For example, the request URL:

 

**http ://foo.com/mano/feed/multi/fetch?multi.param\=fetch.url&fetch.url\=A&fetch.url\=B&fetch.url\=C**

 

It will result in 3 forked chain requests each one with a single value for the **fetch.url** parameter.

 
 
* fetch.url\=A
 
* fetch.url\=B
 
* fetch.url\=C
 
 

All the entries for the 3 chain requests will be aggregated in the feed bean returned by the MultiFeedHandler.

 

If the **param** parameter is multivalue then the Multi handler will iterate over all those parameters. For example if **param** values are **fetch.url** and **file.write**:

 

**http ://foo.com/mano/feed/multi/fetch?multi.param\=fetch.url&multi.param\=file.write&fetch.url\=A&fetch.url\=B&fetch.url\=C&file.write\=X&file.write\=Y&file.write\=Z**

 

It will result in 3 forked chain request each one with single values for the **fetch.url** and **file.write** parameters:

 
 
* fetch.url\=A&file.write\=X
 
* fetch.url\=B&file.write\=Y
 
* fetch.url\=C&file.write\=Z
 
 
#### Tracking Handler & Servlet

 

Together the Tracking handler and servlet enable feed usage tracking, clicked links (feed site and entries) and viewed entries.

 

The Tracking handler rewriting URLs within the feed to point to the Tracking servlet and adds an image URL (also pointing to the tracking servlet) to the entries descriptions.

 

The Tracking servlet logs the ID, URL and type of usage (VIEW or CLICK). When a client clicks on a link or displays the description of an entry the Tracking servlet is invoked, it tracks the request using and ID in the URL and redirects to the original request or render a 1 pixel transparent image.

 

The 'tracking.url' init parameter in the Tracking handler defines the URL of the Tracking servlet.

 

The createTrackingId() method in the Tracking handler creating a dummy ID. For serious usage this method should be overriden.

 

The track() method in the Tracking servlet is using a Logger to log tracking information.

 
#### Echo Handler (alias the Uploader)

 

The Echo handler echoes (returns back) the feed POSTed to the Mano servlet.

 

This handler, as opposed to the others in the toolbox, it requires running in a servlet container and it has to be fronted by a servlet that supports POST requests as the ManoServlet does.

 

The POST request must have the handler parameters in the query\-string the content\-type header should be 'application/xml', the payload of the POST request must be a feed XML document and it's charset encoding it is assumed to be same as the one defined in the request.

 

The curl command can be used to test this handler, for example:

 

```

 curl http://localhost:8080/mano/feed/echo \
  --data-binary @~/myfeed.xml --header "content-type: application/xml

```
 

An interesting use of the Echo handler is that together with the File handler it can be use o upload and store feeds, for example:

 

```

 curl http://localhost:8080/mano/feed/file/echo?file.write=myfeed \
  --data-binary @~/myfeed.xml --header "content-type: application/xml

```
 
#### Null Handler

 

The Null handler discards the feed from the response and returns no content. If the response had a feed it is discarded and the repose status code is set to NO CONTENT (204). If the response had an error code the error code is returned.

 
#### Sort Handler

 

The Sort handler sorts entries by title or published date, in ascendent (default) or descendent order.

 

The namespaced parameter by must be title (default) or date. The namespaced parameter order must be asc (default) or desc.

 

NULL values are considered \-infinite for the purposes of ordering.

 
## Building ROME.Mano

 

ROME.Mano is a ROME sub\-project, rome/subprojects/mano.

 

It dependencies are ROME v0.8, JDOM v1.0, ROME Fetcher v0.8 and Servlet API v2.3\+.

 

It uses Maven 1.0.2 to build, maven war.

 
## Sample ROME.Mano URLs

 

Fetching a feed:

 
 
* [http://localhost:8080/mano/feed/fetch?fetch.url\=http://wired.com/rss/index.xml](http://localhost:8080/mano/feed/fetch?fetch.url=http://wired.com/rss/index.xml)
 
 

Fetching and converting a feed:

 
 
* [http://localhost:8080/mano/feed/convert/fetch?convert.to\=atom\_0.3&fetch.url\=http://wired.com/rss/index.xml](http://localhost:8080/mano/feed/convert/fetch?convert.to=atom_0.3&amp;fetch.url=http://wired.com/rss/index.xml)
 
 

Fetching, converting and storing 3 feeds (one at the time):

 
 
* [http://localhost:8080/mano/feed/file/convert/fetch?file.write\=a&convert.to\=atom\_1.0&fetch.url\=http://wired.com/rss/index.xml](http://localhost:8080/mano/feed/file/convert/fetch?file.write=a&amp;convert.to=atom_1.0&amp;fetch.url=http://wired.com/rss/index.xml)
 
* [http://localhost:8080/mano/feed/file/convert/fetch?file.write\=b&convert.to\=atom\_1.0&fetch.url\=http://www.tbray.org/ongoing/ongoing.rss](http://localhost:8080/mano/feed/file/convert/fetch?file.write=b&amp;convert.to=atom_1.0&amp;fetch.url=http://www.tbray.org/ongoing/ongoing.rss)
 
* [http://localhost:8080/mano/feed/file/convert/fetch?file.write\=acconvert.to\=atom\_1.0&fetch.url\=http://hotdeals.apple.com/newarrivals/rss/newarrivals.xml](http://localhost:8080/mano/feed/file/convert/fetch?file.write=acconvert.to=atom_1.0&amp;fetch.url=http://hotdeals.apple.com/newarrivals/rss/newarrivals.xml)
 
 

Fetching, converting and storing 3 feeds (all at once) and returning a merged feed with all their entries:

 
 
* [http://localhost:8080/mano/feed/multi/file/convert/fetch?multi.param\=file.write&multi.param\=fetch.url&file.write\=wired&fetch.url\=http://wired.com/rss/index.xml&file.write\=cnn\-top&fetch.url\=http://rss.cnn.com/rss/cnn\_topstories.rss&file.write\=cnn\-world&fetch.url\=http://rss.cnn.com/rss/cnn\_world.rss&file.write\=salon\-sports&fetch.url\=http://salon.com/rss/sports.rss&convert.to\=atom\_1.0](http://localhost:8080/mano/feed/multi/file/convert/fetch?multi.param=file.write&amp;multi.param=fetch.url&amp;file.write=wired&amp;fetch.url=http://wired.com/rss/index.xml&amp;file.write=cnn-top&amp;fetch.url=http://rss.cnn.com/rss/cnn_topstories.rss&amp;file.write=cnn-world&amp;fetch.url=http://rss.cnn.com/rss/cnn_world.rss&amp;file.write=salon-sports&amp;fetch.url=http://salon.com/rss/sports.rss&amp;convert.to=atom_1.0)
 
 

Reading 3 feeds previously written to disk, merging their entries and paginating the feed:

 
 
* [http://localhost:8080/mano/feed/multi/branding/file?&multi.param\=file.read&file.read\=wired&file.read\=cnn\-top&file.read\=cnn\-world&file.read\=salon\-sports](http://localhost:8080/mano/feed/multi/branding/file?&amp;multi.param=file.read&amp;file.read=wired&amp;file.read=cnn-top&amp;file.read=cnn-world&amp;file.read=salon-sports)
 
 

Same as previous feed but with tracking, pagination and sorted:

 
 
* [http://localhost:8080/mano/feed/tracking/sort/paginate/multi/branding/file?paginate.offset\=10&paginate.size\=20&multi.param\=file.read&file.read\=wired&file.read\=cnn\-top&file.read\=cnn\-world&file.read\=salon\-sports&sort.by\=title&sort.order\=desc](http://localhost:8080/mano/feed/tracking/sort/paginate/multi/branding/file?paginate.offset=10&amp;paginate.size=20&amp;multi.param=file.read&amp;file.read=wired&amp;file.read=cnn-top&amp;file.read=cnn-world&amp;file.read=salon-sports&amp;sort.by=title&amp;sort.order=desc)
 
    