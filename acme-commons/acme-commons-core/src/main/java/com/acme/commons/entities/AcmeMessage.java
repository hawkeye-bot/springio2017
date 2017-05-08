package com.acme.commons.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class AcmeMessage {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String payload;

    @Column(name = "MESSAGE_ID")
    private String messageHeaderId;

    @Column(name = "MESSAGE_TYPE")
    private String type;
}
