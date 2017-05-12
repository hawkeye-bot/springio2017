package com.acme.finance.integration;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrintRequest {
    private String name;
    private String address;
    private String content;
}