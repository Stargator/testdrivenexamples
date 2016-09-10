package net.maynard.examples.templateEngine;

import static org.junit.Assert.assertEquals;

import com.gargoylesoftware.base.testing.EqualsTester;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class PlainTextSegmentTest {

    @Test
    public void plainTextEvaluateAsIs() throws Exception {
        Map<String, String> variables = new HashMap<>();
        String text = "abc def";
        assertEquals(text, new PlainText(text).evaluate(variables));
    }

    @Test
    public void testEqualsAndHashCode() {
        final PlainText a = new PlainText("plain text only"); // original object
        final PlainText b = new PlainText("plain text only"); // another object that has the same values as the original
        final PlainText c = new PlainText("5"); // another object with different values
        final PlainText d = new PlainText("plain text only") {
        }; // a subclass of Foo with the same values as the original

        new EqualsTester(a, b, c, d);
    }
}
