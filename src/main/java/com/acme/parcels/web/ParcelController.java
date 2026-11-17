package com.acme.parcels.web;

import com.acme.parcels.model.Parcel;
import com.acme.parcels.model.ScanEvent;
import com.acme.parcels.service.ParcelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/parcels")
public class ParcelController {

    private static final Logger log = LoggerFactory.getLogger(ParcelController.class);

    private final ParcelService parcels;

    public ParcelController(ParcelService parcels) {
        this.parcels = parcels;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parcel> get(@PathVariable long id) {
        return parcels.find(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.info("Tracking lookup miss for parcel {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping("/{id}/scans")
    public Parcel scan(@PathVariable long id, @RequestBody ScanEvent event) {
        return parcels.recordScan(id, event);
    }
}
