package com.acme.commons.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class AcmeMessage {
    @Id
    private Long id;

    @Column
    private String payload;
}
