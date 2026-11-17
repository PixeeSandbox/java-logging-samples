package com.acme.slots.model;

import java.time.LocalDate;

public class Slot {

    private final long warehouseId;
    private final LocalDate date;
    private final int hour;
    private int pallets;
    private Priority priority;

    public Slot(long warehouseId, LocalDate date, int hour) {
        this.warehouseId = warehouseId;
        this.date = date;
        this.hour = hour;
    }

    public long getWarehouseId() {
        return warehouseId;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getHour() {
        return hour;
    }

    public int getPallets() {
        return pallets;
    }

    public Priority getPriority() {
        return priority;
    }

    public boolean isReserved() {
        return priority != null;
    }

    public void reserve(int pallets, Priority priority) {
        this.pallets = pallets;
        this.priority = priority;
    }
}
