import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.classic.filter.ThresholdFilter
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.FileAppender

import static ch.qos.logback.classic.Level.TRACE
import static ch.qos.logback.classic.Level.INFO
import static ch.qos.logback.classic.Level.ERROR

def LOG_PATH = "hiraeth-backend/logs"
def TIMESTAMP = timestamp("yyyy-MM-dd")
def COLORED_LOG_PATTERN = "%d{HH:mm:ss.SSS} [%thread] %highlight(%-5level) %cyan(%logger{36}) - %msg%n"
def LOG_PATTERN = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
scan("30 seconds")

appender("CONSOLE", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = COLORED_LOG_PATTERN
    }
}

/**
 * Filtered console is created to duplicate error messages from com.hiraeth package into console
 * except displaying it only in file.
 */
appender("FILTERED_CONSOLE", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = COLORED_LOG_PATTERN
    }
    filter(ThresholdFilter) {
        level = ERROR
    }
}

appender("FILE", FileAppender) {
    file = "${LOG_PATH}/logfile-${TIMESTAMP}.log"
    encoder(PatternLayoutEncoder) {
        pattern = LOG_PATTERN
    }
}

logger("com.hiraeth", TRACE, ["FILE", "FILTERED_CONSOLE"], additivity=false)
root(INFO, ["FILE", "CONSOLE"])
