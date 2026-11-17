package com.acme.quotes.web;

import com.acme.quotes.model.Quote;
import com.acme.quotes.model.QuoteRequest;
import com.acme.quotes.service.QuoteService;
import java.util.UUID;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quotes")
public class QuoteController {

    private static final Logger log = LogManager.getLogger(QuoteController.class);

    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @PostMapping
    public Quote create(
            @RequestHeader(value = "X-Client-Id", defaultValue = "anonymous") String clientId,
            @Valid @RequestBody QuoteRequest request) {
        log.info("Quote requested by client {} ({} -> {}, ref {})",
                clientId, request.getOrigin(), request.getDestination(), request.getReference());
        return quoteService.create(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quote> get(@PathVariable UUID id) {
        return quoteService.find(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Quote {} not found", id);
                    return ResponseEntity.notFound().build();
                });
    }
}
