package net.maynard.examples.templateEngine.learningTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.Matchers.not;
import static net.maynard.examples.templateEngine.util.HamcrestAddon.assertThat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.maynard.examples.templateEngine.Template;
import org.junit.Test;

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
    public void testFindStartAndEnd() throws Exception {
        String haystack = "The needle shop sells needles, so stop ${asking} ${please}.";
        String firstRegex = "(needle)";
        String secondRegex = "stop";
        String thirdRegex = "\\$\\{asking}";
        String fourthRegex = "\\$\\{please\\}";

        // First Regex
        Matcher matcher = Pattern.compile(firstRegex).matcher(haystack);

        assertTrue(matcher.find());
        assertEquals("Wrong start index of 1st match", 4, matcher.start());
        assertEquals("Wrong end index of 1st match", 10, matcher.end());

        assertTrue(matcher.find());
        assertEquals("Wrong start index of 2nd match", 22, matcher.start());
        assertEquals("Wrong end index of 2nd match", 28, matcher.end());

        assertFalse("Should not have any more matches", matcher.find());

        // Second Regex        
        matcher = Pattern.compile(secondRegex).matcher(haystack);

        assertTrue(matcher.find());
        assertEquals("Wrong start index of 1st match", 34, matcher.start());
        assertEquals("Wrong end index of 1st match", 38, matcher.end());

        assertFalse("Should not have any more matches", matcher.find());

        // Third Regex
        matcher = Pattern.compile(thirdRegex).matcher(haystack);

        assertTrue(matcher.find());
        assertEquals("Wrong start index of 1st match", 39, matcher.start());
        assertEquals("Wrong end index of 1st match", 48, matcher.end());

        assertFalse("Should not have any more matches", matcher.find());

        // Fourth Regex
        matcher = Pattern.compile(fourthRegex).matcher(haystack);

        assertTrue(matcher.find());
        assertEquals("Wrong start index of 1st match", 49, matcher.start());
        assertEquals("Wrong end index of 1st match", 58, matcher.end());

        assertFalse("Should not have any more matches", matcher.find());
    }

    @Test
    public void learningTest() {
        template = new Template("${one}, ${two}, ${three}");
        System.out.println("Template; " + template.getTemplateText());
        System.out.println();

        template.set("one", "one");
        System.out.println("Template; " + template.getTemplateText());
        System.out.println("one");
        System.out.println();

        template.set("two", "{two}");
        System.out.println("Template; " + template.getTemplateText());
        System.out.println("two");
        System.out.println();

        template.set("three", "$three");
        System.out.println("Template; " + template.getTemplateText());
        System.out.println("three");
        System.out.println();

        assertTemplateEvaluatesTo("one, {two}, $three");
    }

    private void assertTemplateEvaluatesTo(String expected) {
        assertEquals(expected, template.evaluate());
    }
}
