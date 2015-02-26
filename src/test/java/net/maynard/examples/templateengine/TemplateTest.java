package net.maynard.examples.templateengine;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Stargator
 */
public class TemplateTest {

    @Test
    final public void oneVariableTest() throws Exception {
        Template template = new Template("Hello, ${name}.");
        template.set("name", "Reader");
        assertEquals("Hello, Reader.", template.evaluate());
    }

    @Test
    final public void differentTemplateTest() throws Exception {
        Template template = new Template("Hi, ${name}.");
        template.set("name", "someone else");
        assertEquals("Hi, someone else.", template.evaluate());
    }
}
