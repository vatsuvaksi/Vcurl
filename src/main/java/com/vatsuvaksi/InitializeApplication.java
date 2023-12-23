package com.vatsuvaksi;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class InitializeApplication {

    private InitializeApplication(){}
    protected static void start() {
        // Create a Global Logger
        GlobalConfigLogging.configureGlobalLogger();
    }

    private static class GlobalConfigLogging{
        protected static void configureGlobalLogger() {
            // Remove existing handlers to avoid duplicate log messages
            Logger rootLogger = Logger.getLogger("");
            for (java.util.logging.Handler handler : rootLogger.getHandlers()) {
                rootLogger.removeHandler(handler);
            }

            // Create console handler and set its level
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.INFO);

            // Create a formatter and associate it with the console handler
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            consoleHandler.setFormatter(simpleFormatter);

            // Add the console handler to the root logger
            rootLogger.addHandler(consoleHandler);

            // Set the root logger level (controls the level of messages that will be logged)
            rootLogger.setLevel(Level.INFO);
        }
    }
}
