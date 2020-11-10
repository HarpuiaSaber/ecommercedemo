package com.toan.ecommercedemo.enums;

import java.util.HashMap;
import java.util.Map;

public enum ProductStatus {
    DRAFT(0), WAITINGFORACCEPT(1), ACCEPTED(2), DENY(3), ON(4), OFF(5);
    private final int value;
    private static Map<Integer, ProductStatus> map = new HashMap<Integer, ProductStatus>();

    private ProductStatus(int value) {
        this.value = value;
    }

    static {
        for (ProductStatus option : ProductStatus.values()) {
            map.put(option.value, option);
        }
    }

    public static ProductStatus valueOf(int value) {
        return map.get(value);
    }

    public int getValue() {
        return value;
    }
}
