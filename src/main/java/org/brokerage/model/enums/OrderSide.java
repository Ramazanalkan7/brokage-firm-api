package org.brokerage.model.enums;

public enum OrderSide {
    BUY,
    SELL;

    public boolean isBuy() {
        return this == BUY;
    }

    public boolean isSell() {
        return this == SELL;
    }
}
