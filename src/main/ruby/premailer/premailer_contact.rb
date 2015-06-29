require "java"
require "premailer"

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

  def initialize
  end

  def init(html, options)
    options.keys.each do |key|
      options[(key.to_sym rescue key) || key] = options.delete(key)
    end

    @defaultOption = {:adapter => :nokogiri, :input_encoding => 'UTF-8', }
    
    @pr = Premailer.new(html, options.merge(@defaultOption))
  end


  def inline_css
    return @pr.to_inline_css
  end


  def plain_text
    return @pr.to_plain_text
  end

end

PremailerContact.new