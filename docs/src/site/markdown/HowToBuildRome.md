::: section
## How to build Rome?

Rome is built using mighty
[Maven](http://maven.apache.org/){.externalLink}! This is why these
instructions are so short:-)

If you don\'t want to build Rome we\'d suggest you download a binary
build:-)

::: section
### Check out from GitHub

See [GitHub](https://github.com/rometools/rome){.externalLink} for
access intructions and to browse the source from your browser.
:::

::: section
### Setup maven

See [Installing
Maven](http://maven.apache.org/run-maven/index.html){.externalLink} for
details.

Maven automatically downloads dependencies of a project from an online
repository.
:::

::: section
### Build rome.jar

At the command prompt type:

```
    > mvn package
```

This will build

```
    rome/target/rome-VERSION.jar
```

You\'re good to go.

::: section
#### Build a full binary distribution

```
    > mvn install
```

::: section
#### Build the project site

```
    > mvn site
```

::: section
### For Rome developers

::: section
#### Work on Rome using Eclipse

```
    > mvn eclipse:eclipse
```

will setup the Eclipse project for you.