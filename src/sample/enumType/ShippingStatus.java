package sample.enumType;

import java.util.HashMap;
import java.util.Map;

public enum ShippingStatus {
    WAITING(1), PAID(2), SHIPPED(3), DELIVERED(4);

    private final int value;

    ShippingStatus(int value) {
        this.value = value;
    }

    public int getShippingStatValue() {
        return value;
    }
}
