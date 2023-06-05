package ru.ufanet.coffeeshoporderingsystem.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
@EntityListeners(AuditingEntityListener.class)
public class ProductEntity extends BaseEntity {

    private UUID id;

    private String name;

    private BigDecimal price;

}
