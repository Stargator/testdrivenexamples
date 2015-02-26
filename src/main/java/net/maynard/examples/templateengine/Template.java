package net.maynard.examples.templateengine;

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

    final private Map<String, String> variables;

    final private String templateText;

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

        return result;
    }

    private void checkForMissingValues(String textToCheck) {
        Matcher matcher = Pattern.compile(".*\\$\\{.+\\}.*").matcher(textToCheck);

        if (matcher.find()) {
            throw new MissingValueException("No value set for " + matcher.group());
        }
    }

    private String replaceVariablesWithValues() {
        String localTempText = this.templateText;

        for (Entry<String, String> entry : this.variables.entrySet()) {
            String regex = "\\$\\{" + entry.getKey() + "\\}";
            localTempText = localTempText.replaceAll(regex, entry.getValue());
        }

        // TODO If result matches templateText it should throw an exception
        return localTempText;
    }
}
