package ar.edu.ubp.das.src.utils;

import java.util.Random;

public class Utils {
    public static int getRandom(final int maxLimit) {
        return Math.abs(new Random().nextInt(maxLimit));
    }
}
