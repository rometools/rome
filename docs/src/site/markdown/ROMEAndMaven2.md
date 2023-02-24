::: section
## ROME and Maven 2

this page is not up to date

Starting with ROME 1.0 RC2, rome jars are deployed on the [java.net
maven repository](http://download.java.net/maven/2/){.externalLink}.

In your project you can add this repository by putting the following XML
into your pom.xml

```xml
    <repository>
      <id>maven2-repository.dev.java.net</id>
      <name>Java.net Repository for Maven</name>
      <url>http://download.java.net/maven/2/</url>
      <layout>default</layout>
    </repository>
```

As of 22, April 2009, the jars are deployed for the following releases:

-   ROME 1.0
-   ROME Fetcher 1.0
-   ROME Modules 0.3.2

To include the Rome 1.0 in your maven2 project:

```xml
    <dependency>
      <groupId>rome</groupId>
      <artifactId>rome</artifactId>
      <version>1.0</version>
    </dependency>
```

\-- Main.mj\_ - 22 Apr 2009
:::
