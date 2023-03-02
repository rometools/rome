## Installation

To use Rome you can either use Rome from Maven Central or build Rome yourself.

### Install from Maven Central

All versions since 1.5.0 are available on Maven Central under the following 
coordinates:

```xml
<dependency>
    <groupId>com.rometools</groupId>
    <artifactId>rome</artifactId>
    <version>${rome.version}</version>
</dependency>
```

Some earlier versions are available on Maven Central under another groupId:

```xml
<dependency>
    <groupId>rome</groupId>
    <artifactId>rome</artifactId>
    <version>${rome.version}</version>
</dependency>
```

### Install from source

If you want to build the library from source you just have clone the source from
[GitHub](https://github.com/rometools/rome) and install the package using the 
[Maven Wrapper](https://maven.apache.org/wrapper/). Please note that JDK 8 is
required to install Rome from source.

**Linux**
```shell
git clone https://github.com/rometools/rome.git
cd rome
./mvnw install
```

**Windows**
```shell
git clone https://github.com/rometools/rome.git
cd rome
mvnw install
```