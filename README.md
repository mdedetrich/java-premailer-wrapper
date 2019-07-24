# Java Premailer Wrapper

## What is this?

This java wrapper around [Premailer](https://github.com/premailer/premailer). It is roughly based on the
wrapper from [here](https://github.com/r-shah/java-premailer-wrapper), with a few important differences

- It works
- Premailer is brought in as a dependency rather than being directly included in the source. This allows you
to easily bump Premailer as a dependency
- Added a method that allows you to terminate a Premailer instance (`PremailerInterface.destroyInstance`)
- A fix for JDK 1.8 in regards to conflicts for `.merge ` method (see [here](https://github.com/jruby/jruby/issues/1249))
- The `Premailer` class no longer acts like a pseudo singleton. Its up to the user to manage the instance (typically you
would store this in a Singleton to reuse the `PremailerInstance`)
- Uses a versioning scheme to identify between Premailer releases, i.e. 1.0_1.8.7 means version 1 using Premailer 1.8.7

## Dependency Info

Currently hosted on maven central, with the following details

```xml
<dependency>
	  <groupId>org.mdedetrich</groupId>
	  <artifactId>java-premailer-wrapper</artifactId>
	  <version>1.2_1.8.7</version>
</dependency>
```

If you haven't already done so, you need to add the `Rubygems` maven repository, i.e.

```xml
<repository>
    <id>rubygems-releases</id>
    <url>http://rubygems-proxy.torquebox.org/releases</url>
</repository>
```

## Building

You can build a jar by doing

```
mvn compile
mvn package
```

## Usage

To use, do something like this

```java
String testHtml = "<html><head></head><body><p>test</p></body></html>";

// Create a Premailer
Premailer premailer = new Premailer()

// Get the instance
PremailerInterface premailerInterface = premailer.getPremailerInstance();
    
// Pass your options in form of HashMap
Map<String, Object> options = new HashMap<String, Object>( );
    
// Pass at least this option for html string
options.put( "with_html_string", true );
    
System.out.print( premailerInterface.plain_text(testHtml, options) );
System.out.print( premailerInterface.inline_css(testHtml, options) );
    
// Shut it down
premailer.destroyInstance();     
```
