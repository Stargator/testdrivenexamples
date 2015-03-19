package net.maynard.examples.templateengine;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.maynard.examples.templateengine.exceptions.MissingValueException;

/**
 *
 * @author root
 */
class Template {

    private Map<String, String> variables;
    private String templateText;
    private String evaluatedText;

    public Template(String initialTemplateText) {
        this.variables = new HashMap<>();
        this.templateText = initialTemplateText;
    }

    public void set(String name, String value) {
        this.variables.put(name, value);
    }

    public String evaluate() {
        String result = replaceVariablesWithValues();
        // TODO: Make new test to handle if there is no variable set in the template text

        checkForMissingValues(result);
        setEvaluatedText(result);

        return result;
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

//    public String getTemplateText() {
//        return this.templateText;
//    }
//
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
