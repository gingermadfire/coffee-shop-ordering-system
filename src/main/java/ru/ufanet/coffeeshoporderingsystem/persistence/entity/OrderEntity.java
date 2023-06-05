package ru.ufanet.coffeeshoporderingsystem.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
public class OrderEntity extends BaseEntity {

    private UUID id;

    private List<UUID> productIds;

    private UUID clientId;

    private UUID employeeId;

    private Instant estimatedIssueTime;

    private BigDecimal totalPrice;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    private String orderCancellingReason;

}
