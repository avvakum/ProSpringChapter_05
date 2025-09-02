package com.avva.five.manual;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class Concert implements Performance {
    private static Logger LOGGER = LoggerFactory.getLogger(Concert.class);

    @Override
    public void execute() {
        LOGGER.info(" ... La la la la laaaa ...");
        try {
            Thread.sleep(Duration.ofMillis(2000).toMillis());
        } catch (InterruptedException e) {
        }
    }
}
