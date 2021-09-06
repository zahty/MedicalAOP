package com.m2i.demomedical.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggingHelper {
    private Logger logger = LoggerFactory.getLogger(LoggingHelper.class);

    private static LoggingHelper instance = new LoggingHelper();

    private LoggingHelper() {
    }

    public static LoggingHelper getInstance() {
        if (instance == null)
            instance = new LoggingHelper();
        return instance;
    }

    public void log(final String content, final LogLevel level) {
        switch (level) {
            case INFO:
                logger.info(content);
                break;
            case WARN:
                logger.warn(content);
                break;
            case ERROR:
                logger.error(content);
                break;
            default:
                logger.debug(content);
        }
    }

    public enum LogLevel {
        INFO, DEBUG, WARN, ERROR
    }

}