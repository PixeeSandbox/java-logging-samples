package com.acme.invoices.web;

import com.acme.invoices.model.InvoiceRequest;
import java.util.Map;
import java.util.UUID;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private static final Logger log = LogManager.getLogger(InvoiceController.class);

    @PostMapping
    public Map<String, Object> create(
            @RequestHeader(value = "User-Agent", defaultValue = "unknown") String userAgent,
            @Valid @RequestBody InvoiceRequest request) {
        UUID invoiceId = UUID.randomUUID();
        log.info("Invoice {} for customer {} amount {} memo '{}' (agent: {})",
                invoiceId, request.getCustomerId(), request.getAmount(), request.getMemo(), userAgent);
        return Map.of("id", invoiceId, "status", "DRAFT");
    }
}
