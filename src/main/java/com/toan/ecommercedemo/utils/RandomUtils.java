package com.toan.ecommercedemo.utils;

import java.util.Random;

public class RandomUtils {
    public static int getRandomIntInRange(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
