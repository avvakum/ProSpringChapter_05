package com.avva.five.common;

import com.avva.five.common_2.Guitar;
import com.avva.five.common_2.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnnotatedGuitarist implements Singer {
    private static Logger logger = LoggerFactory.getLogger(AnnotatedGuitarist.class);

    @Override
    public void sing() {
    }

    @AdviceRequired
    public void sing(Guitar guitar) {
        logger.info("play: " + guitar.play());
    }
}
