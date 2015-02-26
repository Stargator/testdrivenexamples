package net.maynard.examples.templateengine;

import net.maynard.examples.templateengine.exceptions.MissingValueException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Stargator
 */
public class TemplateTest {

    private static Template template;

    @Before
    public void setUp() throws Exception {
        template = new Template("${one}, ${two}, ${three}.");
        template.set("one", "1");
        template.set("two", "2");
        template.set("three", "3");
    }

    @Test
    public void missingValueRaisesException() throws Exception {
        try {
            new Template("${foo}").evaluate();
            fail("evaluate() should throw an exception if a variable was left "
                    + "without a value!");
        } catch (MissingValueException expected) {
            assertEquals("No value set for ${foo}", expected.getMessage());
        }
    }

    @Test
    public void multipleAndDifferentVariables() throws Exception {
        assertTemplateEvaluatesTo("1, 2, 3.");
    }

    @Test
    public void unknownVariablesAreIgnored() throws Exception {
        template.set("doesnotexist", "whatever");
        assertTemplateEvaluatesTo("1, 2, 3.");
    }

    private void assertTemplateEvaluatesTo(String expected) {
        assertEquals(expected, template.evaluate());
    }
}
