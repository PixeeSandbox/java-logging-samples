package com.acme.returns.model;

import java.time.Instant;

public class ReturnAuthorization {

    private final String rmaNumber;
    private final String orderNumber;
    private final Instant createdAt;

    public ReturnAuthorization(String rmaNumber, String orderNumber, Instant createdAt) {
        this.rmaNumber = rmaNumber;
        this.orderNumber = orderNumber;
        this.createdAt = createdAt;
    }

    public String getRmaNumber() {
        return rmaNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
