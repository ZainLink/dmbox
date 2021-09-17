package com.zkzy.portal.common.utils;

import java.security.SecureRandom;
import java.util.Objects;
import java.util.Random;

/**
 * @author edward
 * @date 2017/11/12
 */
public class RandomString {

    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = UPPER.toLowerCase();
    private static final String DIGITS = "0123456789";
    private static final String ALPHA_NUMBER = UPPER + LOWER + DIGITS;

    private static final int MIN_STRING_LENGTH = 1;
    private static final int MIN_SYMBOLS_LENGTH = 2;

    private final Random random;
    private final char[] symbols;
    private final char[] buffer;

    public RandomString(int length, Random random, String symbols) {
        if (length < MIN_STRING_LENGTH) {
            throw new IllegalArgumentException();
        }
        if (symbols.length() < MIN_SYMBOLS_LENGTH) {
            throw new IllegalArgumentException();
        }
        this.random = Objects.requireNonNull(random);
        this.symbols = symbols.toCharArray();
        this.buffer = new char[length];
    }

    /**
     * Create an alphanumeric string generator.
     */
    public RandomString(int length, Random random) {
        this(length, random, ALPHA_NUMBER);
    }

    /**
     * Create an alphanumeric strings from a secure generator.
     */
    public RandomString(int length) {
        this(length, new SecureRandom());
    }

    /**
     * Create session identifiers.
     */
    public RandomString() {
        this(32);
    }

    /**
     * Generate a random string.
     */
    public String nextString() {
        for (int index = 0; index < buffer.length; ++index) {
            buffer[index] = symbols[random.nextInt(symbols.length)];
        }

        return new String(buffer);
    }
}
