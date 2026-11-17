package com.acme.returns.service;

import com.acme.returns.model.ReturnAuthorization;
import com.acme.returns.model.ReturnRequest;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReturnService {

    private static final Logger log = LoggerFactory.getLogger(ReturnService.class);

    private final AtomicLong sequence = new AtomicLong(40000);
    private final Map<String, ReturnAuthorization> authorizations = new ConcurrentHashMap<>();

    public ReturnAuthorization open(String storeId, ReturnRequest request) {
        String rma = "RMA-" + sequence.incrementAndGet();
        ReturnAuthorization auth = new ReturnAuthorization(rma, request.getOrderNumber(), Instant.now());
        authorizations.put(rma, auth);
        log.info("Opened {} for order {} (store {}, customer {}): {}",
                rma, request.getOrderNumber(), storeId, request.getEmail(), request.getReason());
        return auth;
    }

    public Optional<ReturnAuthorization> find(String rmaNumber) {
        ReturnAuthorization auth = authorizations.get(rmaNumber);
        if (auth == null) {
            log.info("Unknown RMA lookup: {}", rmaNumber);
        }
        return Optional.ofNullable(auth);
    }
}
