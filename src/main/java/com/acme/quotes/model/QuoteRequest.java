package com.acme.quotes.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class QuoteRequest {

    @NotBlank
    private String origin;

    @NotBlank
    private String destination;

    @Positive
    private double weightKg;

    private String reference;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(double weightKg) {
        this.weightKg = weightKg;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
