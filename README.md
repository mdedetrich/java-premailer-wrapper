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
- Uses a versioning scheme to identify between Premailer releases, i.e. 1.0_1.8.4 means version 1 using Premailer 1.8.4

## Usage

If you haven't already done so, you need to add the `Rubygems` maven repository, i.e.

```xml
		<repository>
			<id>rubygems-releases</id>
			<url>http://rubygems-proxy.torquebox.org/releases</url>
		</repository>
```

Then to use, do something like this

```java
		String testHtml = "<html><head></head><body><p>test</p></body></html>";
		
		// Create a Premailer
		Premailer premailer = new Premailer()
		
        // Get the instance
        PremailerInterface premailerInterface = Premailer.getInstance();
        
        // Pass your options in form of HashMap
        Map<String, Object> options = new HashMap<String, Object>( );
        
        // Pass at least this option for html string
        options.put( "with_html_string", true );
        
        // Initialize premailer with html and options
        premailerInterface.init( testHtml, options );
        
        System.out.print( premailerInterface.plain_text( ) );
        System.out.print( premailerInterface.inline_css( ) );
        
        // Shut it down
        premailerInterface.destroyInstance();
        
```