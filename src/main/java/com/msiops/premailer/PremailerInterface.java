package com.msiops.premailer;

import java.util.Map;

public interface PremailerInterface {

    public Object init( String html, Map<String, Object> options );

    String inline_css();

    String plain_text();
}

