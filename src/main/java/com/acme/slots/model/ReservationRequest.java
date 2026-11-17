package com.acme.slots.model;

public class ReservationRequest {

    private int pallets;
    private Priority priority = Priority.STANDARD;

    public int getPallets() {
        return pallets;
    }

    public void setPallets(int pallets) {
        this.pallets = pallets;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
