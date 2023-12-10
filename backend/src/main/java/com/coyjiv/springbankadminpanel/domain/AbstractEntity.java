package com.coyjiv.springbankadminpanel.domain;

import jakarta.persistence.*;
import lombok.Data;
@Data
@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
}