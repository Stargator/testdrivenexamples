package net.maynard.examples.templateEngine;

import java.util.Map;
import java.util.Objects;

import net.maynard.examples.templateEngine.exceptions.MissingValueException;

public class Variable implements Segment {
    private String name;

    public Variable(String name) {
        setName(name);
    }

    @Override
    public String evaluate(Map<String, String> variables) {
        String currName = getName();

        if (!variables.containsKey(currName)) {
            throw new MissingValueException(currName);
        }

        return variables.get(currName);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newText) {
        this.name = newText;
    }

    @Override
    public boolean equals(Object other) {
        boolean outcome = false;

        if (other != null && other.getClass() == this.getClass()) {
            if (Objects.equals(getName(), ((Variable) other).name)) {
                outcome = true;
            }
        }

        return outcome;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(getName());
        return hash;
    }
}
