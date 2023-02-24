::: section
## Slash

This plug in is for use with feeds from Slash-based weblogs.

::: section
### Sample Usage

```java
    SyndEntry entry = new SyndEntryImpl();
    // set up the entry...
    Slash slash = new SlashImpl();
    slash.setComments( new Integer( 12 ) );
    slash.setDepartment( "look-another-rome-plugin" );
    slash.setSection("code");
    slash.setHitParade( new Integer[] { new Integer(12), new Integer(0) } );
    List modules = new ArrayList();
    modules.add( slash );
    entry.setModules( modules );

    //Optionally, to get Slash information from a Feed:
    Slash slash = entry.getModule( Slash.URI );
    System.out.println( slash.getComments() );
```

::: section
### Changes

::: section
#### 0.2

Initial release from ROME.
:::
:::
:::
