package com.avva.five.annotated;

import com.avva.five.common_2.Singer;
import com.avva.five.common_2.Guitar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("johnMayer")
public class GrammyGuitarist implements Singer {
    private static Logger LOGGER = LoggerFactory.getLogger(GrammyGuitarist.class);

    @Override
    public void sing() {
        LOGGER.info("sing: Wild blue, deeper than I ever knew");
    }

    public void sing(Guitar guitar) {
        LOGGER.info("play: " + guitar.play());
    }

    public void talk() {
        LOGGER.info("talk");
    }

    @Override
    public void rest() {
        LOGGER.info("zzz");
    }
}
