package net.maynard.examples.templateengine.util;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;

/**
 * Hamcrest v1.2 has some annoying behavior with generics. This class works around some of those
 * problems.
 */
public class HamcrestAddon {
    /**
     * Sometimes hamcrest's generic support is too difficult to work with; this utility method
     * provides a way to avoid warnings/errors related to generics
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void assertThat(Object o, Matcher matcher) {
        assertThat("", o, matcher);
    }

    /**
     * Sometimes hamcrest's generic support is too difficult to work with; this utility method
     * provides a way to avoid warnings/errors related to generics
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void assertThat(String reason, Object o, Matcher matcher) {
        MatcherAssert.assertThat(reason, o, matcher);
    }
}