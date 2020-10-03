package com.toan.ecommercedemo.enums;

import java.util.HashMap;
import java.util.Map;

public enum Gender {
    MALE(0), FEMALE(1);
    private final int value;
    private static Map<Integer, Gender> map = new HashMap<Integer, Gender>();

    private Gender(int value) {
        this.value = value;
    }

    static {
        for (Gender gender : Gender.values()) {
            map.put(gender.value, gender);
        }
    }

    public static Gender valueOf(int value) {
        return map.get(value);
    }

    public int getValue() {
        return value;
    }
}
