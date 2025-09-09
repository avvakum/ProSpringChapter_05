package com.avva.five.common_2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Guitarist implements Singer {
    private static Logger LOGGER = LoggerFactory.getLogger(Guitarist.class);

    @Override
    public void sing() {
        LOGGER.info("Just keep me where the light is");
    }

    public void sing2() {
        LOGGER.info("And wrap me in your arms");
    }

    @Override
    public void rest() {
        LOGGER.info("zzz...zzz...zzz...");
    }
}
