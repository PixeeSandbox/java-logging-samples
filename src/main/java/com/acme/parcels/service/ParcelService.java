package com.acme.parcels.service;

import com.acme.parcels.model.Parcel;
import com.acme.parcels.model.ScanEvent;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ParcelService {

    private static final Logger log = LoggerFactory.getLogger(ParcelService.class);

    private final Map<Long, Parcel> parcels = new ConcurrentHashMap<>();

    public Optional<Parcel> find(long id) {
        return Optional.ofNullable(parcels.get(id));
    }

    public Parcel recordScan(long id, ScanEvent event) {
        if (event.getScannedAt() == null) {
            event.setScannedAt(Instant.now());
        }
        Parcel parcel = parcels.computeIfAbsent(id, Parcel::new);
        parcel.getScans().add(event);
        log.info("Parcel {} scanned {} at hub {} ({} scans total)",
                id, event.getType(), event.getHubId(), parcel.getScans().size());
        return parcel;
    }
}
