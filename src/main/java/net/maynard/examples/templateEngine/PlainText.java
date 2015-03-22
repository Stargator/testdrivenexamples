package net.maynard.examples.templateEngine;

import java.util.Map;
import java.util.Objects;

public class PlainText implements Segment {
    private String text;

    public PlainText(String text) {
        setText(text);
    }

    @Override
    public String evaluate(Map<String, String> variables) {
        return this.text;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String newText) {
        this.text = newText;
    }

    @Override
    public boolean equals(Object other) {
        boolean outcome = false;

        if (other != null && other.getClass() == this.getClass()) {
            if (Objects.equals(this.text, ((PlainText) other).text)) {
                outcome = true;
            }
        }

        return outcome;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.text);
        return hash;
    }
}