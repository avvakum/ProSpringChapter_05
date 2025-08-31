package com.avva.five.common_2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GreatGuitarist implements Singer {
    private static Logger LOGGER = LoggerFactory.getLogger(GreatGuitarist.class);

    @Override
    public void sing() {
        LOGGER.info("You're got my soul in your hand");
    }
}
