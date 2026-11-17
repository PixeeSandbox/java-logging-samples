package com.acme.invoices.model;

import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class InvoiceRequest {

    @NotBlank
    private String customerId;

    @Positive
    private BigDecimal amount;

    private String memo;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
