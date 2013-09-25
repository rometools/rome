# Rss and atOM utilitiEs (ROME) Plugins Mechanism


ROME has been designed around a plugin mechanism. All the supported feed types (RSSs and Atom) is done by plugins included in the distribution.



Parsing feeds, generating feeds, converting feeds from a concrete feed to a SyndFeed and vice versa, parsing modules and generating modules is done using plugins.



Plugins for new functionality can be added and default plugins can be easily replaced with alternate plugins.


## Plugins definition files



Plugins are defined in a properties file, the **_rome.properties_** file.



The default plugins definition file is included in the ROME JAR file, **_com/sun/syndication/rome.properties_**, this is the first plugins definition file to be processed. It defines the default parsers, generators and converters for feeds and modules ROME provides.



After loading the default plugins definition file, ROME looks for additional plugins definition files in all the CLASSPATH entries, this time at root level, **_/rome.properties_**. And appends the plugins definitions to the existing ones. Note that if there are several **_/rome.properties_** files in the different CLASSPATH entries all of them are processed. The order of processing depends on how the **_ClassLoader_** processes the CLASSPATH entries, this is normally done in the order of appearance \-of the entry\- in the CLASSPATH.



For each type of plugin (parser, generator, converter, ect) a list of available plugins is built following the read order just described. The plugins classes are then loaded and instantiated. All plugins have some kind of primary key. In the case or parsers, generators and converters the primary key is the type of feed they handle. In the case of modules, the primary key is the module URI. If a plugin list definition (the aggregation of all the plugins of the same time from all the **_rome.properties_**) contains more than one plugin with the same primary key, the latter one is the one that will be used(this enables replacing default plugins with custom ones).



The plugins are read, loaded and managed by the implementation class **_com.sun.syndication.io.impl.PluginManager_**. This class is an abstract class and it is extended to provide support for each type of plugin.


## Parser Plugins



Parser plugins are managed by the **_com.sun.syndication.io.impl.FeedParsers_** class (subclass of the **_PluginManager_**). This plugin manager looks for the **_WireFeedParser.classes_** property in all the **_rome.properties_** files. The fully qualified names of the parser classes must be separated by whitespaces or commas. For example, the default **_rome.properties_** file parser plugins definition is as follows:



```

# Feed Parser implementation classes
#
WireFeedParser.classes=com.sun.syndication.io.impl.RSS090Parser \
                       com.sun.syndication.io.impl.RSS091NetscapeParser \
                       com.sun.syndication.io.impl.RSS091UserlandParser \
                       com.sun.syndication.io.impl.RSS092Parser \
                       com.sun.syndication.io.impl.RSS093Parser \
                       com.sun.syndication.io.impl.RSS094Parser \
                       com.sun.syndication.io.impl.RSS10Parser  \
                       com.sun.syndication.io.impl.RSS20wNSParser  \
                       com.sun.syndication.io.impl.RSS20Parser  \
                       com.sun.syndication.io.impl.Atom03Parser

```


All the classes defined in this property have to implement the **_com.sun.syndication.io.WireFeedParser_** interface. Parser instances must be thread safe. The return value of the **_getType()_** method is used as the primary key. If more than one parser returns the same type, the latter one prevails.


## Generator Plugins



Generator plugins are managed by the **_com.sun.syndication.io.impl.FeedGenerators_** class (subclass of the **_PluginManager_**). This plugin manager looks for the **_WireFeedGenerator.classes_** property in all the **_rome.properties_** files. The fully qualified names of the generator classes must be separated by whitespaces or commas. For example, the default **_rome.properties_** file generator plugins definition is as follows:



```

# Feed Generator implementation classes
#
WireFeedGenerator.classes=com.sun.syndication.io.impl.RSS090Generator \
                          com.sun.syndication.io.impl.RSS091NetscapeGenerator \
                          com.sun.syndication.io.impl.RSS091UserlandGenerator \
                          com.sun.syndication.io.impl.RSS092Generator \
                          com.sun.syndication.io.impl.RSS093Generator \
                          com.sun.syndication.io.impl.RSS094Generator \
                          com.sun.syndication.io.impl.RSS10Generator  \
                          com.sun.syndication.io.impl.RSS20Generator  \
                          com.sun.syndication.io.impl.Atom03Generator

```


All the classes defined in this property have to implement the **_com.sun.syndication.io.WireFeedGenerator_** interface. Generator instances must be thread safe. The return value of the **_getType()_** method is used as the primary key. If more than one generator returns the same type, the latter one prevails.


## Converter Plugins



Converter plugins are managed by the **_com.sun.syndication.synd.impl.Converters_** class (subclass of the **_PluginManager_**). This plugin manager looks for the **_Converter.classes_** property in all the **_rome.properties_** files. The fully qualified names of the converter classes must be separated by whitespaces or commas. For example, the default **_rome.properties_** file converter plugins definition is as follows:



```

# Feed Conversor implementation classes
#
Converter.classes=com.sun.syndication.feed.synd.impl.ConverterForAtom03 \
                  com.sun.syndication.feed.synd.impl.ConverterForRSS090 \
                  com.sun.syndication.feed.synd.impl.ConverterForRSS091Netscape \
                  com.sun.syndication.feed.synd.impl.ConverterForRSS091Userland \
                  com.sun.syndication.feed.synd.impl.ConverterForRSS092 \
                  com.sun.syndication.feed.synd.impl.ConverterForRSS093 \
                  com.sun.syndication.feed.synd.impl.ConverterForRSS094 \
                  com.sun.syndication.feed.synd.impl.ConverterForRSS10  \
                  com.sun.syndication.feed.synd.impl.ConverterForRSS20

```


All the classes defined in this property have to implement the **_com.sun.syndication.synd.Converter_** interface. Converter instances must be thread safe. The return value of the **_getType()_** method is used as the primary key. If more than one converter returns the same type, the latter one prevails.


## Module Plugins



There are 2 types of module plugins, module parser plugins and module generator plugins. They use a same pattern feed parsers and generators use.



The main difference is that support for module plugins has to be wired in the feed parser and generator plugins. The default feed parser and generator plugins supporting module plugins are: RSS 1.0, RSS 2.0 and Atom 0.3.



It is important to understand that this wiring is for modules support. Once a feed parser or generator has modules support, new modules can be used just by adding them to right property in the **_rome.properties_** file. No code changes are required.



Module parsers and generators are defined at feed and item level. This allow selective handling of modules, for example handling Syndication module at feed level only.



Module parser plugins are managed by the **_com.sun.syndication.io.impl.ModuleParsers_** class (subclass of the **_PluginManager_**). This plugin manager looks for the **_.feed.ModuleParser.classes_** and the **_.item.ModuleParser.classes_** properties in all the **_rome.properties_** files. must be the type defined by the parser (ie: rss\_1.0, atom\_0.3). The fully qualified names of the module parser classes must be separated by whitespaces or commas. For example, the default **_rome.properties_** file modules parser plugins definition is as follows:



```

# Parsers for Atom 0.3 feed modules
#
atom_0.3.feed.ModuleParser.classes=com.sun.syndication.io.impl.SyModuleParser \
                          com.sun.syndication.io.impl.DCModuleParser

# Parsers for Atom 0.3 entry modules
#
atom_0.3.item.ModuleParser.classes=com.sun.syndication.io.impl.DCModuleParser

# Parsers for RSS 1.0 feed modules
#
rss_1.0.feed.ModuleParser.classes=com.sun.syndication.io.impl.SyModuleParser \
                          com.sun.syndication.io.impl.DCModuleParser

# Parsers for RSS 1.0 item modules
#
rss_1.0.item.ModuleParser.classes=com.sun.syndication.io.impl.DCModuleParser

# Parsers for RSS 2.0 feed modules
#
rss_2.0.feed.ModuleParser.classes=

# Parsers for RSS 2.0 item modules
#
rss_2.0.item.ModuleParser.classes=

```


All the classes defined in this property have to implement the **_com.sun.syndication.io.ModuleParser_** interface. ModuleParser instances must be thread safe. The return value of the **_getNamesapceUri()_** method is used as the primary key. If more than one module parser returns the same URI, the latter one prevails.



Module generator plugins are managed by the **_com.sun.syndication.io.impl.GeneratorParsers_** class (subclass of the **_PluginManager_**). This plugin manager looks for the **_.feed.ModuleGenerator.classes_** and the **_.item.ModuleGenerator.classes_** properties in all the **_rome.properties_** files. must be the type defined by the generator (ie: rss\_1.0, atom\_0.3). The fully qualified names of the module generator classes must be separated by whitespaces or commas. For example, the default **_rome.properties_** file modules generator plugins definition is as follows:



```

# Generators for Atom 0.3 feed modules
#
atom_0.3.feed.ModuleGenerator.classes=com.sun.syndication.io.impl.SyModuleGenerator \
                             com.sun.syndication.io.impl.DCModuleGenerator

# Generators for Atom 0.3 entry modules
#
atom_0.3.item.ModuleGenerator.classes=com.sun.syndication.io.impl.DCModuleGenerator

# Generators for RSS 1.0 feed modules
#
rss_1.0.feed.ModuleGenerator.classes=com.sun.syndication.io.impl.SyModuleGenerator \
                             com.sun.syndication.io.impl.DCModuleGenerator

# Generators for RSS_1.0 entry modules
#
rss_1.0.item.ModuleGenerator.classes=com.sun.syndication.io.impl.DCModuleGenerator

# Generators for RSS 2.0 feed modules
#
rss_2.0.feed.ModuleGenerator.classes=

# Generators for RSS_2.0 entry modules
#
rss_2.0.item.ModuleGenerator.classes=

```


All the classes defined in this property have to implement the **_com.sun.syndication.io.ModuleGenerator_** interface. ModuleGenerator instances must be thread safe. The return value of the **_getNamesapceUri()_** method is used as the primary key. If more than one module generator returns the same URI, the latter one prevails.



See also: a step\-by\-step [tutorial for implementing a custom module](RssAndAtOMUtilitiEsROMEV0.5TutorialDefiningACustomModuleBeanParserAndGenerator.html).

