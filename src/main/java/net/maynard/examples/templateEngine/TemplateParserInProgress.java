package net.maynard.examples.templateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.maynard.examples.templateEngine.util.Config;

public class TemplateParserInProgress {

    final public String varStarting = Config.varStarting;
    final public String varEnding = Config.varEnding;

    //Original
//    private List<String> parse(String template) {
//        List<String> segments = new ArrayList<>();
//        int index = collectSegments(segments, template);
//
//        addTailingPlainText(segments, template, index);
//        addEmptyStringIfTemplateWasEmpty(segments);
//
//        return segments;
//    }

    public List<Segment> parseSegments(String template) {
        List<Segment> segments = new ArrayList<>();
        collectSegments(segments, template);

        return segments;
    }

//    public List<Segment> parseSegments(String template) {
//        List<Segment> segments = new ArrayList<>();
//        int index = collectSegments(segments, template);
//
//        addTail(segments, template, index);
//        addEmptyStringIfTemplateWasEmpty(segments);
//
//        return segments;
//    }

    private void collectSegments(List<Segment> segments, String template) {
        Pattern pattern = Pattern.compile("\\$\\{[^}]*\\}");
        Matcher matcher = pattern.matcher(template);

        while (matcher.find()) {
            String strToEval = template.substring(matcher.start(), matcher.end());

            if (isVariable(strToEval)) {
                segments.add(new Variable(strToEval));
            } else {
                segments.add(new PlainText(strToEval));
            }
        }

        addEmptyStringIfTemplateWasEmpty(segments);
    }

//    private void addTailingPlainText(List<Segment> segments, String template, int index) {
//        if (index < template.length()) {
//            segments.add(new PlainText(template.substring(index)));
//        }
//    }

//    private void addVariable(List<Segment> segments, String template, Matcher matcher) {
//        String possibleVar = template.substring(matcher.start(), matcher.end());
//
//        if (isVariable(possibleVar)) {
//            segments.add(new Variable(possibleVar));
//        }
//    }

//    private void addPrecedingPlainText(List<Segment> segments, String template, Matcher matcher, int index) {
//        if (index != matcher.start()) { // Getting IllegalStateException at matcher.start()
//            segments.add(new PlainText(template.substring(index, matcher.start())));
//        }
//    }

    private void addEmptyStringIfTemplateWasEmpty(List<Segment> segments) {
        if (segments.isEmpty()) {
            segments.add(new PlainText(""));
        }
    }

//    public List<Segment> parseSegments(String template) {
//        List<Segment> segments = new ArrayList<>();
//        List<String> strings = parse(template);
//
//        for(String str : strings) {
//            if (isVariable(str)) {
//                addVariable(str);
//            } else {
//                segments.add(new PlainText(str));
//            }
//        }
//
//        return segments;
//    }

    private boolean isVariable(String segment) {
        return segment.startsWith(varStarting) && segment.endsWith(varEnding);
    }
}
