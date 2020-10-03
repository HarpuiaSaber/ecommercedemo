package com.toan.ecommercedemo.enums;

import java.util.HashMap;
import java.util.Map;

public enum CommentStatus {
    WAITTINGFORACCEPT(0), APPROVED(1), DENIED(2);
    private final int value;
    private static Map<Integer, CommentStatus> map = new HashMap<Integer, CommentStatus>();

    private CommentStatus(int value) {
        this.value = value;
    }

    static {
        for (CommentStatus status : CommentStatus.values()) {
            map.put(status.value, status);
        }
    }

    public static CommentStatus valueOf(int value) {
        return map.get(value);
    }

    public int getValue() {
        return value;
    }
}
