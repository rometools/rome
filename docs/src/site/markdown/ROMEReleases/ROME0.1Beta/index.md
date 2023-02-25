## ROME 0.1 Beta (Jun 08, 2004)

### Dependencies

-   J2SE 1.4+ (Not J2SE 1.3+ as it was initially documented)
-   [JDOM Beta 10](http://www.jdom.org/)
-   [Jakarta Commons Codec 1.2](http://jakarta.apache.org/commons/codec/)
-   Rome v0.1 does not depend on Xerces as it was initially documented

### Downloads

-   [rome-0.1.zip](./rome-0.1.zip)
-   [rome-0.1.tar.gz](./rome-0.1.tar.gz)
-   [rome-0.1-src.zip](./rome-0.1-src.zip)
-   [rome-0.1-src.tar.gz](./rome-0.1-src.tar.gz)

### TODO Remove
-   [rome-samples-0.1-src.zip](./rome-samples-0.1-src.zip)
-   [rome-samples-0.1.tar.gz](./rome-samples-0.1.tar.gz)
-   [rome-samples-0.1.zip](./rome-samples-0.1.zip)
-   [rome-samples-0.1-src.tar.gz](./rome-samples-0.1-src.tar.gz)

### Known Issues

-   On Mac OS X 10.2.8 Maven cannot run the samples, the JVM exist with
    a reflection error in a native method
-   On Mac OS X 10.2.8 to run the samples using Ant you need to include
    Xerces 2.4.0 in the CLASSPATH. Otherwise it does not the XML Parser
    implementation
