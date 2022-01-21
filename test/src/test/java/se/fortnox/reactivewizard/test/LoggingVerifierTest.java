package se.fortnox.reactivewizard.test;

import org.apache.logging.log4j.Level;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class LoggingVerifierTest {
    private static final Logger LOG = LoggerFactory.getLogger(LoggingVerifierTest.class);

    @Rule
    public LoggingVerifier loggingVerifier = new LoggingVerifier(LoggingVerifierTest.class, Level.TRACE);

    @Test
    public void verifyLogging() {
        LOG.trace("trace");
        LOG.debug("debug");
        LOG.info("info");
        LOG.warn("warn");
        LOG.error("error");

        loggingVerifier.verify(Level.TRACE, "trace");
        loggingVerifier.verify(Level.DEBUG, "debug");
        loggingVerifier.verify(Level.INFO, logEvent -> assertThat(logEvent.getMessage().getFormattedMessage()).isEqualTo("info"));
        loggingVerifier.verify(Level.WARN, "warn");
        loggingVerifier.verify(Level.ERROR, "error");
    }
}
