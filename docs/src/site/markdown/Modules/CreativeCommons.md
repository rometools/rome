::: section
## Creative Commons

This plug in is for use with feeds from Creative Commons license.

This module provides a unified rights and license system for both the
RSS2/Atom and RSS/RDF namespace. However, if you wish to **generate**
RDF/RSS feeds, you need to use a CVS build of ROME (or a version higher
than 0.8).

::: section
### Sample Usage

```java
    CreativeCommons commons = new CreativeCommonsImpl();
    commons.setLicense( new License[]{ License.NONCOMMERCIAL } );
    // Note, you do not have to setAllLicenses right now. When the RSS1 functionality is
    // added, this will be required at the Feed level only.
    ArrayList modules = new ArrayList()
    modules.add( commons );
    syndEntry.setModules( commons );

    //Alternately, to get the module:
    CreativeCommons commons = (CreativeCommons) syndFeed.getModule( CreativeCommons.URI );
```

::: section
### Changes

::: section
#### 0.2

Initial release from ROME.
:::
:::
:::
