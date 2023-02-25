## How to build Rome?

Rome is built using mighty
[Maven](http://maven.apache.org/)! This is why these
instructions are so short:-)

If you don\'t want to build Rome we\'d suggest you download a binary
build:-)

### Check out from GitHub

See [GitHub](https://github.com/rometools/rome) for
access intructions and to browse the source from your browser.

### Setup maven

See [Installing
Maven](http://maven.apache.org/run-maven/index.html) for
details.

Maven automatically downloads dependencies of a project from an online
repository.

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

#### Build a full binary distribution

```
> mvn install
```

#### Build the project site

```
> mvn site
```

### For Rome developers

#### Work on Rome using Eclipse

```
> mvn eclipse:eclipse
```

will setup the Eclipse project for you.