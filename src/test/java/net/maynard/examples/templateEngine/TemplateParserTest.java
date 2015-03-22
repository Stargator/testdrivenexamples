package net.maynard.examples.templateEngine;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TemplateParserTest {

    @Test
    public void parsingTemplatesIntoSegmentObjects() throws Exception {
        String templateStr = "a ${b} c ${d}";
        TemplateParser parser = new TemplateParser();
        List<Segment> segments = parser.parseSegments(templateStr);
        assertSegments(segments,
                new PlainText("a "), new Variable("b"),
                new PlainText(" c "), new Variable("d"));
    }

    @Test
    public void emptyTemplateRenderAsEmptyString() throws Exception {
        String templateStr = "";
        processTemplateString(templateStr, templateStr);
    }

    @Test
    public void templateWithOnlyPlainText() throws Exception {
        String templateStr = "plain text only";
        processTemplateString(templateStr, templateStr);
    }

    @Test
    public void parsingMultipleVariables() throws Exception {
        String templateStr = "${a}:${b}:${c}";
        processTemplateString(templateStr, "${a}", ":", "${b}", ":", "${c}");
    }

    private void processTemplateString(String templateStr, Object... expected) {
        List<String> segments = parse(templateStr);
        assertSegments(segments, expected);
    }

    private List<String> parse(String template) {
        return new TemplateParser().parse(template);
    }

    private void assertSegments(List<? extends Object> actual, Object... expected) {
        assertEquals("Number of segments does not match", expected.length, actual.size());
        assertEquals(Arrays.asList(expected), actual);
        System.out.println("Expected: " + Arrays.asList(expected));
        System.out.println("Actual  : " + actual);
    }
}
