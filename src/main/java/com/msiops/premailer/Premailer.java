package com.msiops.premailer;

import org.jruby.embed.PathType;
import org.jruby.embed.ScriptingContainer;

/**
 * Class that holds the Premailer Interface instance
 */

public final class Premailer {
    
    private ScriptingContainer container;
    private PremailerInterface instance;
    private Boolean customContainer = false;
    private Boolean terminated = false;
    
    private static String path = "premailer/premailer_contact.rb";

    /**
     * Creates a new Premailer. Note that you only need to create this once, its recommended you
     * store this in a Singleton so you reuse it for multiple calls
     */
    
    public Premailer(){
        container = new ScriptingContainer();
        container.setClassLoader(container.getClass().getClassLoader());
        Object receiver = container.runScriptlet(PathType.CLASSPATH, path);
        
        // Create instance of premailer interface
        instance = container.getInstance( receiver,
                PremailerInterface.class );
    }

    /**
     * Creates a Premailer out of an already existing JRuby Scripting Container. Useful if you already use
     * JRuby elsewhere in your application
     * @param container The container for Premailer to use
     */
    
    public Premailer(ScriptingContainer container){
        if (container == null) {
            throw new IllegalArgumentException();
        } else {
            this.container = container;
            customContainer = true;
            Object receiver = container.runScriptlet(PathType.CLASSPATH, path);
            instance = container.getInstance( receiver,
                    PremailerInterface.class );
        }
    }

    /**
     * Retrieves a Premailer instance, use this to interface with Premailer
     * @return The Premailer Instance
     */
    
    public PremailerInterface getPremailerInstance() {
        return instance;
    }

    /**
     * Returns the JRuby Scripting Container, shouldn't really need this
     * @return JRuby Scripining Container
     */
    
    public ScriptingContainer getScriptingContainer() {
        return container;
    }

    /**
     * Whether or not the Premailer was created with a custom Scripting Container
     * @return Premailer created with custom scripting container
     */
    
    public Boolean usesCustomScriptingContainer() {
        return customContainer;
    }

    /**
     * Destroy's a the permailer instance
     */
    
    public void destroyInstance() {
        if (terminated) {
            throw new IllegalArgumentException("Premailer already terminated");
        }
        
        if (instance != null) {
            instance = null;
        }
        
        if (container != null) {
            container.terminate();
            terminated = true;
        }
    }
}

