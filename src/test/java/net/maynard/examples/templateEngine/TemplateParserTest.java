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
        PlainText expected = new PlainText(templateStr);
        processTemplateString(templateStr, expected);
    }

    @Test
    public void templateWithOnlyPlainText() throws Exception {
        String templateStr = "plain text only";
        PlainText expected = new PlainText(templateStr);
        processTemplateString(templateStr, expected);
    }

    @Test
    public void parsingMultipleVariables() throws Exception {
        String templateStr = "${a}:${b}:${c}";
        Variable firstExpected = new Variable("a");
        PlainText secondExpected = new PlainText(":");
        Variable thirdExpected = new Variable("b");
        PlainText fourthExpected = new PlainText(":");
        Variable fifthExpected = new Variable("c");

        processTemplateString(templateStr,
                firstExpected, secondExpected,
                thirdExpected, fourthExpected,
                fifthExpected);
    }

    private void processTemplateString(String templateStr, Object... expected) {
        List<Segment> segments = parse(templateStr);
        assertSegments(segments, expected);
    }

    private List<Segment> parse(String template) {
        return new TemplateParser().parseSegments(template);
    }

    private void assertSegments(List<? extends Object> actual, Object... expected) {
        assertEquals("Number of segments does not match", expected.length, actual.size());
        assertEquals(Arrays.asList(expected), actual);
//        System.out.println("Expected: " + Arrays.asList(expected));
//        System.out.println("Actual  : " + actual);
    }
}
