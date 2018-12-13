package com.msiops.premailer;

import java.util.Map;

public interface PremailerInterface {

    String inline_css( String html, Map<String, Object> options );

    String plain_text( String html, Map<String, Object> options );
}

