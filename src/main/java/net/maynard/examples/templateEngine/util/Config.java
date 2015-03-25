package net.maynard.examples.templateEngine.util;

public interface Config {
    // TODO: Later pull these values from a property/config file
    public final String varStarting = "${";
    public final String varEnding = "}";
    public final String logFileLocation = "/root/Desktop/GitHub_Projects/timeLog.txt";
}
