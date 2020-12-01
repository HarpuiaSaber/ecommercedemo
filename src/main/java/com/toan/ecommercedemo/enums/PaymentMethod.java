package com.toan.ecommercedemo.enums;

import java.util.HashMap;
import java.util.Map;

public enum PaymentMethod {
    COD(0), PAYPAL(1);
    private final int value;
    private static Map<Integer, PaymentMethod> map = new HashMap<Integer, PaymentMethod>();

    private PaymentMethod(int value) {
        this.value = value;
    }

    static {
        for (PaymentMethod status : PaymentMethod.values()) {
            map.put(status.value, status);
        }
    }

    public static PaymentMethod valueOf(int value) {
        return map.get(value);
    }

    public int getValue() {
        return value;
    }
}
