package net.maynard.examples.templateEngine.util;

public interface Config {
    // TODO: Later pull these values from a property/config file
    String varStarting = "${";
    String varEnding = "}";
    String logFileLocation = "/root/Desktop/GitHub_Projects/timeLog.txt";
}
