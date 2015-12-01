package net.maynard.examples.templateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Template {

    final private Map<String, String> variables;
    private String templateText;
    private String evaluatedText;

    public Template(String initialTemplateText) {
        this.variables = new HashMap<>();
        this.templateText = initialTemplateText;
    }

    public void set(String name, String value) {
        this.variables.put(name, value);
    }

    // Parses the template and then renders the result
    public String evaluate() {
        TemplateParser parser = new TemplateParser();
        List<Segment> segments = parser.parseSegments(getTemplateText());
        setEvaluatedText(concatenate(segments));

        return getEvaluatedText();
    }

    private String concatenate(List<Segment> segments) {
        StringBuilder result = new StringBuilder(segments.size());
        // TODO: Make new test to handle if there is no variable set in the template text

        for(Segment segment : segments) {
            result.append(segment.evaluate(this.variables));
        }

        return result.toString();
    }

    public String getTemplateText() {
        return this.templateText;
    }

    public void setTemplateText(String newText) {
        this.templateText = newText;
    }

//    public Map<String, String> getVariables() {
//        return Collections.unmodifiableMap(this.variables);
//    }
//
//    public void setVariables(Map<String, String> newVariables) {
//        this.variables = newVariables;
//    }

    public String getEvaluatedText() {
        return this.evaluatedText;
    }

    public void setEvaluatedText(String newEvaluatedText) {
        this.evaluatedText = newEvaluatedText;
    }
}
