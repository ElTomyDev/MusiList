package com.heavydelay.util;

import java.security.SecureRandom;

public class AccessCodeGenerator {
    private static final String MAYUS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String MINUS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMS = "0123456789";
    private static final String CHARACTERS = MAYUS + MINUS + NUMS;
    private static final SecureRandom random = new SecureRandom();

    public static String generateAccessCode(int length){
        if (length < 1 || length > 6){
            throw new IllegalArgumentException("The code length must be greater than 1 and less than 6");
        }

        StringBuilder code = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(index));
        }
    return code.toString();
    }
}
