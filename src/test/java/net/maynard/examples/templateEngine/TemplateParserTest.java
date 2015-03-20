package net.maynard.examples.templateEngine;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TemplateParserTest {

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

    private void processTemplateString(String templateStr, String... expected) {
        List<String> segments = parse(templateStr);
        assertSegments(segments, expected);
    }

    private List<String> parse(String template) {
        return new TemplateParser().parse(template);
    }

    private void assertSegments(List<? extends Object> actual, Object... expected) {
        assertEquals("Number of segments does not match", expected.length, actual.size());
        assertEquals(Arrays.asList(expected), actual);
    }
}
