package net.maynard.examples.templateengine;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author root
 */
class Template {

    private Map<String, String> variables;

    private String templateText;

    public Template(String initialTemplateText) {
        this.variables = new HashMap<String, String>();
        this.templateText = initialTemplateText;
    }

    public void set(String name, String value) {
        this.variables.put(name, value);
    }

    public String evaluate() {
        String result = this.templateText;

        for (Entry<String, String> entry : this.variables.entrySet()) {
            String regex = "\\$\\{" + entry.getKey() + "\\}";
            result = result.replaceAll(regex, entry.getValue());
        }

        return result;
    }
}
