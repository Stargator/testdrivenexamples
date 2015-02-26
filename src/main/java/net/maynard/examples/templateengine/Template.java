package net.maynard.examples.templateengine;

/**
 *
 * @author root
 */
class Template {

    private String variableValue;

    public Template(String templateText) {
    }

    public void set(String variable, String value) {
        this.variableValue = value;
    }

    public String evaluate() {
        return "Hello, " + this.variableValue + ".";
    }
    
}
