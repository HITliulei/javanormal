package com.ll;

import java.util.Random;

/**
 * @author Lei
 * @version 0.1
 * @date 2021/6/24
 */
public class Enums {
    private static Random rand = new Random(47);

    public static <T extends Enum<T>> T random(Class<T> ec) {
        return random(ec.getEnumConstants());
    }

    public static <T> T random(T[] values) {
        return values[rand.nextInt(values.length)];
    }
}

