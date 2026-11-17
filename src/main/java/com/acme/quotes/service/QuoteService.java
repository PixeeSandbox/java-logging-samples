package com.acme.quotes.service;

import com.acme.quotes.model.Quote;
import com.acme.quotes.model.QuoteRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class QuoteService {

    private static final Logger log = LogManager.getLogger(QuoteService.class);

    private static final BigDecimal BASE_RATE = new BigDecimal("12.50");
    private static final BigDecimal PER_KG = new BigDecimal("0.85");

    private final Map<UUID, Quote> quotes = new ConcurrentHashMap<>();

    public Quote create(QuoteRequest request) {
        BigDecimal price = BASE_RATE
                .add(PER_KG.multiply(BigDecimal.valueOf(request.getWeightKg())))
                .setScale(2, RoundingMode.HALF_UP);
        Quote quote = new Quote(UUID.randomUUID(), request.getOrigin(), request.getDestination(), price);
        quotes.put(quote.getId(), quote);
        log.debug("Stored quote {} priced at {}", quote.getId(), price);
        return quote;
    }

    public Optional<Quote> find(UUID id) {
        return Optional.ofNullable(quotes.get(id));
    }
}
