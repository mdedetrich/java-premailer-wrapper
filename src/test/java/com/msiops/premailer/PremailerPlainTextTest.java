package com.msiops.premailer;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;

import com.msiops.premailer.Premailer;
import com.msiops.premailer.PremailerInterface;

public class PremailerPlainTextTest {

    @Rule
    public final ContiPerfRule i = new ContiPerfRule();

    final PremailerInterface premailer = new Premailer().getPremailerInstance();

    @Test
    public void shouldPlainText() throws Exception {
        final String html = "<html><head><style>body{ margin: 0; }</style></head><body>Sample Text</body></html>";

        final Map<String, Object> options = new HashMap<>();
        options.put("with_html_string", true);

        final String inline = premailer.plain_text(html, options);

        assertThat(inline, is(notNullValue()));
        assertThat(inline, is("Sample Text"));
    }

    @PerfTest(invocations = 1000, threads = 5)
    @Test
    public void shouldPlainTextInThreadSafeManner() throws Exception {
        final String html = "<html><head><style>body{ margin: 0; }</style></head><body>Sample Text</body></html>";

        final Map<String, Object> options = new HashMap<>();
        options.put("with_html_string", true);

        final String inline = premailer.plain_text(html, options);

        assertThat(inline, is(notNullValue()));
        assertThat(inline, is("Sample Text"));
    }
}
