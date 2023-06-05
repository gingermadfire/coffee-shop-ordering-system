package ru.ufanet.coffeeshoporderingsystem.event.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderCancelledEvent extends AbstractOrderEvent {

    private final String eventName = "OrderCancelledEvent";

    @JsonProperty("orderId")
    private final UUID orderId;

    @JsonProperty("employeeId")
    private final UUID employeeId;

    @JsonProperty("orderCancellingReason")
    private String orderCancellingReason;

}
