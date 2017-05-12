package com.acme.finance.messaging.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ExecutePaymentRequest {
    private BigDecimal amount;
    private Integer accountNumber;
}
