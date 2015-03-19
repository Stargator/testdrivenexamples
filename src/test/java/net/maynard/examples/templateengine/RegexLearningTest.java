package net.maynard.examples.templateengine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.Matchers.not;
import static net.maynard.examples.templateengine.util.HamcrestAddon.assertThat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author root
 */
public class RegexLearningTest {

    private Template template;

    @Test
    public void testHowGroupCountWorks() throws Exception {
        String haystack = "The needle shop sells needles";
        String regex = "(needle)";
        Matcher matcher = Pattern.compile(regex).matcher(haystack);
        assertThat(matcher.groupCount(), not(2));
    }

    @Test
    public void testHowGroupCountWorks2() throws Exception {
        String haystack = "The needle shop sells needles, so stop ${asking} ${please}.";
        String firstRegex = "(needle)";
        String secondRegex = "stop";
        String thirdRegex = "\\$\\{asking}";
        String fourthRegex = "\\$\\{please\\}";
        Matcher matcher = Pattern.compile(firstRegex).matcher(haystack);

        assertTrue(matcher.find());
        assertEquals("Wrong start index of 1st match", 4, matcher.start());
        assertEquals("Wrong end index of 1st match", 10, matcher.end());

        assertTrue(matcher.find());
        assertEquals("Wrong start index of 2nd match", 22, matcher.start());
        assertEquals("Wrong end index of 2nd match", 28, matcher.end());

        assertFalse("Should not have any more matches", matcher.find());

        matcher = Pattern.compile(secondRegex).matcher(haystack);

        assertTrue(matcher.find());
        assertEquals("Wrong start index of 1st match", 34, matcher.start());
        assertEquals("Wrong end index of 1st match", 38, matcher.end());

        assertFalse("Should not have any more matches", matcher.find());

        matcher = Pattern.compile(thirdRegex).matcher(haystack);

        assertTrue(matcher.find());
        assertEquals("Wrong start index of 1st match", 39, matcher.start());
        assertEquals("Wrong end index of 1st match", 48, matcher.end());

        assertFalse("Should not have any more matches", matcher.find());

        matcher = Pattern.compile(fourthRegex).matcher(haystack);

        assertTrue(matcher.find());
        assertEquals("Wrong start index of 1st match", 49, matcher.start());
        assertEquals("Wrong end index of 1st match", 58, matcher.end());

        assertFalse("Should not have any more matches", matcher.find());
    }

    @Test
    public void learningTest() {
        template = new Template("${one}, ${two}, $three");
//        System.out.println("Template; " + template.getTemplateText());
//        System.out.println("Variables: " + template.getVariables());
//        System.out.println();

        template.set("one", "one");
//        System.out.println("Template; " + template.getTemplateText());
//        System.out.println("one");
//        System.out.println();

        template.set("two", "{two}");
//        System.out.println("Template; " + template.getTemplateText());
//        System.out.println("two");
//        System.out.println();
//
//        template.set("three", "${two}");
//        System.out.println("Template; " + template.getTemplateText());
//        System.out.println("three");
//        System.out.println();

        assertTemplateEvaluatesTo("one, {two}, $three");
    }

    private void assertTemplateEvaluatesTo(String expected) {
        assertEquals(expected, template.evaluate());
        System.out.println("Text; " + template.getEvaluatedText());
        System.out.println("Evaluated");
    }
}
