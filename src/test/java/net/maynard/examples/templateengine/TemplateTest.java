package net.maynard.examples.templateengine;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Stargator
 */
public class TemplateTest {

    private static Template template;

    @BeforeClass
    static public void setUp() throws Exception {
        template = new Template("${one}, ${two}, ${three}.");
        template.set("one", "1");
        template.set("two", "2");
        template.set("three", "3");
    }

    @Test
    final public void multipleAndDifferentVariables() throws Exception {
        assertTemplateEvaluatesTo("1, 2, 3.");
    }

    @Test
    final public void unknownVariablesAreIgnored() throws Exception {
        template.set("doesnotexist", "whatever");
        assertTemplateEvaluatesTo("1, 2, 3.");
    }

    private void assertTemplateEvaluatesTo(String expected) {
        assertEquals(expected, template.evaluate());
    }
}
