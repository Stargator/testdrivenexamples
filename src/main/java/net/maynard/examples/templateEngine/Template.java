package net.maynard.examples.templateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.maynard.examples.templateEngine.exceptions.MissingValueException;

/**
 *
 * @author root
 */
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
        List<String> segments = parser.parse(this.templateText);
        StringBuilder result = new StringBuilder();
        // TODO: Make new test to handle if there is no variable set in the template text

        for(String segment : segments) {
            append(segment, result);
        }

        setEvaluatedText(result.toString());

        return result.toString();
    }

    private void append(String segment, StringBuilder result) {
        String varStarting = "${";
        String varEnding = "}";

        if (segment.startsWith(varStarting) && segment.endsWith(varEnding)) {
            String variable = segment.substring(varStarting.length(), segment.length() - varEnding.length());

            if (!variables.containsKey(variable)) {
                throw new MissingValueException("No value set for " + segment);
            }

            result.append(variables.get(variable));
        } else {
            result.append(segment);
        }
    }

    private void checkForMissingValues(String textToCheck) {
        Matcher matcher = Pattern.compile(".*\\$\\{.+\\}.*").matcher(textToCheck);

        if (matcher.find()) {
            throw new MissingValueException("No value set for " + matcher.group());
        }
    }

//    public void process(Matcher matcher, Entry<String, String> entry) {
//            matcher.replaceAll(entry.getValue());
//    }

    private String replaceVariablesWithValues() {
        String localTempText = this.templateText;

        for (Entry<String, String> entry : this.variables.entrySet()) {
            Matcher matcher = Pattern.compile("\\$\\{" + entry.getKey() + "\\}").matcher(localTempText);
            localTempText = matcher.replaceAll(entry.getValue());
        }

        // TODO If result matches templateText it should throw an exception
        return localTempText;
    }

    public String getTemplateText() {
        return this.templateText;
    }

    public void setTemplateText(String newText) {
        this.templateText = newText;
    }
//
//    public Map<String, String> getVariables() {
//        return Collections.unmodifiableMap(this.variables);
//    }
//
//    public void setVariables(Map<String, String> newVariables) {
//        this.variables = newVariables;
//    }
//
    public String getEvaluatedText() {
        return this.evaluatedText;
    }

    public void setEvaluatedText(String newEvaluatedText) {
        this.evaluatedText = newEvaluatedText;
    }
}
