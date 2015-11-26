package net.maynard.examples.templateEngine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import com.gargoylesoftware.base.testing.EqualsTester;
import net.maynard.examples.templateEngine.exceptions.MissingValueException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VariableSegmentTest {

    private Map<String, String> variables;
    private String name;
    private String value;

    @Before
    public void setUp() {
        variables = new HashMap<>();
        name = "myvar";
    }

    @After
    public void tearDown() {
        variables = null;
        name = null;
        value = null;
    }

    @Test
    public void variableEvaluatesToItsValue() throws Exception {
        String value = "myvalue";
        variables.put(name, value);

        assertEquals(value, new Variable(name).evaluate(variables));
    }

    @Test
    public void missingVariableRaisesException() throws Exception {
        try {
            new Variable(name).evaluate(variables);
            fail("Missing variable value should raise an exception");
        } catch (MissingValueException expected) {
            assertEquals("No value set for ${" + name + "}", expected.getMessage());
        }
    }

    @Test
    public void testEqualsAndHashCode() {
        final Variable a = new Variable("4"); // original object
        final Variable b = new Variable("4"); // another object that has the same values as the original
        final Variable c = new Variable("5"); // another object with different values
        final Variable d = new Variable("4") {
        }; // a subclass of Foo with the same values as the original

        new EqualsTester(a, b, c, d);
    }
}
