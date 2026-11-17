package com.acme.parcels.model;

import java.util.ArrayList;
import java.util.List;

public class Parcel {

    private final long id;
    private final List<ScanEvent> scans = new ArrayList<>();

    public Parcel(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public List<ScanEvent> getScans() {
        return scans;
    }

    public ScanType getStatus() {
        return scans.isEmpty() ? null : scans.get(scans.size() - 1).getType();
    }
}
