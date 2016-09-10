package net.maynard.examples.templateEngine;

import java.util.Map;

public interface Segment {
    String evaluate (Map<String, String> variables);
}
