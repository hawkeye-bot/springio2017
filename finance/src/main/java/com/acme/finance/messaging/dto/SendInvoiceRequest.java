package com.acme.finance.messaging.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendInvoiceRequest {
    private String name;
    private String address;
    private String content;
}
