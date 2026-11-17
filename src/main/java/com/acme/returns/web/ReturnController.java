package com.acme.returns.web;

import com.acme.returns.model.ReturnAuthorization;
import com.acme.returns.model.ReturnRequest;
import com.acme.returns.service.ReturnService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/returns")
public class ReturnController {

    private final ReturnService returns;

    public ReturnController(ReturnService returns) {
        this.returns = returns;
    }

    @PostMapping
    public ResponseEntity<ReturnAuthorization> open(
            @RequestHeader(value = "X-Store-Id", defaultValue = "web") String storeId,
            @RequestBody ReturnRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(returns.open(storeId, request));
    }

    @GetMapping("/{rmaNumber}")
    public ResponseEntity<ReturnAuthorization> get(@PathVariable String rmaNumber) {
        return returns.find(rmaNumber)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
