package ru.job4j.io.loggin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        char ch = 'm';
        boolean bool = true;
        byte bt = 126;
        short sh = 2561;
        int in = 40;
        long lg = 200;
        float fl = 45.6F;
        double db = 100D;
        LOG.debug("Primitives - char : {}, boolean : {}, byte : {}, short : {},"
                        + " int : {}, long : {}, float : {}, double : {}",
                ch, bool, bt, sh, in, lg, fl, db);
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
    }
}
