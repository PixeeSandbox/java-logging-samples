package com.acme.quotes.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Quote {

    private final UUID id;
    private final String origin;
    private final String destination;
    private final BigDecimal price;

    public Quote(UUID id, String origin, String destination, BigDecimal price) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
