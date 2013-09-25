# How to build Rome?


Rome is built using mighty [Maven](http://maven.apache.org/)! This is why these instructions are so short:\-)



If you don't want to build Rome we'd suggest you download a binary build:\-)


## Check out from Subversion



See [Version Control](https://rometools.jira.com/source/browse/ROME) for access intructions and to browse the source from your browser.



Check out the tag version\-0\-1 for version 0.1. Else checkout the trunk for the development version.


## Setup maven



See [Installing Maven](http://maven.apache.org/run-maven/index.html) for details.



Maven automatically downloads dependencies of a project from an online repository.


### Build rome.jar



At the command prompt type:



```

> mvn jar:jar

```


This will build



```

rome/target/rome-VERSION.jar

```


You're good to go.


### Build a full binary distribution



```

> mvn

```

### Build the project site



```

> mvn site

```

## For Rome developers


### Work on Rome using Eclipse



```

> mvn eclipse:eclipse

```


will setup the Eclipse project for you.



We don't use the site:deploy or dist:deploy targets yet since java.net is not very maven\-friendly yet:\-)

