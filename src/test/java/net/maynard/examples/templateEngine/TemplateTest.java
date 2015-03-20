package net.maynard.examples.templateEngine;

import net.maynard.examples.templateEngine.exceptions.MissingValueException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Stargator
 */
public class TemplateTest {

    private Template template;

    @Before
    public void setUp() throws Exception {
        template = new Template("${one}, ${two}, ${three}.");
        template.set("one", "1");
        template.set("two", "2");
        template.set("three", "3");
    }

    @Test
    public void variablesGetProcessedJustOnce() throws Exception {
        template.set("one", "${one}");
        template.set("two", "${three}");
        template.set("three", "${two}");
        assertTemplateEvaluatesTo("${one}, ${three}, ${two}.");
    }

    @Test
    public void missingValueRaisesException() throws Exception {
        try {
            new Template("${bar}").evaluate();
            fail("evaluate() should throw an exception if a variable was left "
                    + "without a value!");
        } catch (MissingValueException expected) {
            assertEquals("No value set for ${bar}", expected.getMessage());
        }
    }

    @Test
    public void multipleAndDifferentVariables() throws Exception { // Problem, the template text never changed
        assertTemplateEvaluatesTo("1, 2, 3.");
    }

    @Test
    public void unknownVariablesAreIgnored() throws Exception { // Problem, the template text never changed
        template.set("doesnotexist", "whatever");
        assertTemplateEvaluatesTo("1, 2, 3.");
    }

    @Test
    public void emptyTemplateRenderAsEmptyString() throws Exception {
        String templateStr = "";
        this.template.setTemplateText(templateStr);

        assertTemplateEvaluatesTo(templateStr);
    }

    @Test
    public void templateWithOnlyPlainText() throws Exception {
        String templateStr = "plain text only";
        this.template.setTemplateText(templateStr);

        assertTemplateEvaluatesTo(templateStr);
    }

    private void assertTemplateEvaluatesTo(String expected) {
        assertEquals(expected, template.evaluate());
        System.out.println("Text; " + template.getEvaluatedText());
        System.out.println("Evaluated");
    }
}
