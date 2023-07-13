package com.alvarium.utils;

import org.apache.logging.log4j.Logger;

public class LoggerSingleton {

    private static Logger logger;

    private LoggerSingleton() {}

    protected LoggerSingleton build () {
        return this;
    }

    public static Logger getInstance() {
        if (logger == null) {
            throw new RuntimeException();
        }
        return logger;
    }

    public static class LoggerSingletonOptions {

        private LoggerSingletonOptions() {}

        public static void setLogger(Logger logger) {
            LoggerSingleton.logger = logger;
        }
    }
    
}
