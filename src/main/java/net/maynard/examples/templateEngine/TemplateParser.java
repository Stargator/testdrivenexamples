package net.maynard.examples.templateEngine;

import net.maynard.examples.templateEngine.util.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateParser {

    // TODO: Later pull these values from a property/config file
    final private String varStarting = Config.varStarting;
    final private String varEnding = Config.varEnding;

    public List<String> parse(String template) {
        List<String> segments = new ArrayList<>();
        int index = collectSegments(segments, template);

        addTail(segments, template, index);
        addEmptyStringIfTemplateWasEmpty(segments);

        return segments;
    }

    private int collectSegments(List<String> segments, String template) {
        Pattern pattern = Pattern.compile("\\$\\{[^}]*\\}");
        Matcher matcher = pattern.matcher(template);
        int index = 0;

        while (matcher.find()) {
            addPrecedingPlainText(segments, template, matcher, index);
            addVariable(segments, template, matcher);
            index = matcher.end();
        }

        return index;
    }

    private void addTail(List<String> segments, String template, int index) {
        if (index < template.length()) {
            segments.add(template.substring(index));
        }
    }

    private void addVariable(List<String> segments, String template, Matcher matcher) {
        segments.add(template.substring(matcher.start(), matcher.end()));
    }

    private void addVariable(List<Segment> segments, String variableStr) {
        String newVar = variableStr.substring(2, variableStr.length() - 1);
        segments.add(new Variable(newVar));
    }

    private void addPrecedingPlainText(List<String> segments, String template, Matcher matcher, int index) {
        if (index != matcher.start()) { // Getting IllegalStateException at matcher.start()
            segments.add(template.substring(index, matcher.start()));
        }
    }

    private void addEmptyStringIfTemplateWasEmpty(List<String> segments) {
        if (segments.isEmpty()) {
            segments.add("");
        }
    }

    public List<Segment> parseSegments(String template) {
        List<Segment> segments = new ArrayList<>();
        List<String> strings = parse(template);

        for(String str : strings) {
            if (isVariable(str)) {
                addVariable(segments, str);
            } else {
                segments.add(new PlainText(str));
            }
        }

        return segments;
    }

    private boolean isVariable(String segment) {
        return segment.startsWith(varStarting) && segment.endsWith(varEnding);
    }
}
