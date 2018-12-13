require "java"
require "premailer"
require "jruby/synchronized"

#$CLASSPATH << 'target/classes';
java_import "com.msiops.premailer.PremailerInterface"

# see https://github.com/jruby/jruby/issues/1249

if ENV_JAVA['java.specification.version'] >= '1.8'
  class Java::JavaUtil::HashMap
    def merge(other)
      dup.merge!(other)
    end
  end
end

class PremailerContact
  include PremailerInterface
  include JRuby::Synchronized

  def initialize
  end

  def init(html, options)
    options.keys.each do |key|
      options[(key.to_sym rescue key) || key] = options.delete(key)
    end

    @defaultOption = {:adapter => :nokogiri, :input_encoding => 'UTF-8', }

    return Premailer.new(html, options.merge(@defaultOption))
  end


  def inline_css(html, options)
    return init(html, options).to_inline_css
  end


  def plain_text(html, options)
    return init(html, options).to_plain_text
  end

end

PremailerContact.new
