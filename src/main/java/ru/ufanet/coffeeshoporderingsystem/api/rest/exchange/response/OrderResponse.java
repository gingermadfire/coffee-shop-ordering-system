package ru.ufanet.coffeeshoporderingsystem.api.rest.exchange.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ufanet.coffeeshoporderingsystem.persistence.entity.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private UUID id;

    private ClientResponse client;

    private EmployeeResponse employee;

    private List<ProductResponse> products;

    private Instant estimatedIssueTime;

    private OrderStatus currentOrderStatus;

    private String orderCancellingReason;

    private BigDecimal totalPrice;

}