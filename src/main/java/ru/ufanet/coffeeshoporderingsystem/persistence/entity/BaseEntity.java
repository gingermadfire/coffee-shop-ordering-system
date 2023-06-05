package ru.ufanet.coffeeshoporderingsystem.persistence.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.UUID;

@Getter
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue
    private UUID uuid;

    @CreatedDate
    private Instant dateTime;

}
