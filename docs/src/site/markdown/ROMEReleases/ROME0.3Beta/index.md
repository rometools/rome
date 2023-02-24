::: section
## ROME 0.3 Beta

::: section
### Dependencies

-   J2SE 1.4+
-   [JDOM Beta 10](http://www.jdom.org/){.externalLink}
:::

::: section
### Downloads

-   [rome-0.3-src.zip](./rome-0.3-src.zip)
-   [rome-0.3.tar.gz](./rome-0.3.tar.gz)
-   [rome-0.3.zip](./rome-0.3.zip)
-   [rome-0.3-src.tar.gz](./rome-0.3-src.tar.gz)
-   [rome-samples-0.3-src.tar.gz](./rome-samples-0.3-src.tar.gz)
-   [rome-samples-0.3-src.zip](./rome-samples-0.3-src.zip)
:::

::: section
### Additional Information

-   [Tutorials](./RomeV0.3Tutorials/index.html) (2 New ones)
-   Changes Log
:::

::: section
### Known Issues

-   Same issues as Rome v0.1
-   When processing XML documents with DTD (ie: Netscape RSS 0.91) if
    the XML parser implementation is not Xerces and the system does not
    have access ot the internet, the XML parser will fail.
-   If the feed starts with a
    [BOM](http://www.unicode.org/faq/utf_bom.html#BOM){.externalLink}
    the JAXP SAX parser may fail, using [Xerces
    2.6.2](http://xml.apache.org/xerces2-j){.externalLink} addresses the
    problem.
:::
:::
