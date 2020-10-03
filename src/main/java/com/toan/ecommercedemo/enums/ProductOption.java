package com.toan.ecommercedemo.enums;

import java.util.HashMap;
import java.util.Map;

public enum ProductOption {
    NONE(0), SIZE(1), COLOR(2);
    private final int value;
    private static Map<Integer, ProductOption> map = new HashMap<Integer, ProductOption>();

    private ProductOption(int value) {
        this.value = value;
    }

    static {
        for (ProductOption option : ProductOption.values()) {
            map.put(option.value, option);
        }
    }

    public static ProductOption valueOf(int value) {
        return map.get(value);
    }

    public int getValue() {
        return value;
    }
}
