package com.toan.ecommercedemo.enums;

import java.util.HashMap;
import java.util.Map;

public enum OrderStatus {
    WAITTINGFORACCEPT(0), WATTINGFORITEM(1), DELIVERING(2), COMPLETED(3), CANCELLED(4);
    private final int value;
    private static Map<Integer, OrderStatus> map = new HashMap<Integer, OrderStatus>();

    private OrderStatus(int value) {
        this.value = value;
    }

    static {
        for (OrderStatus status : OrderStatus.values()) {
            map.put(status.value, status);
        }
    }

    public static OrderStatus valueOf(int value) {
        return map.get(value);
    }

    public int getValue() {
        return value;
    }
}
