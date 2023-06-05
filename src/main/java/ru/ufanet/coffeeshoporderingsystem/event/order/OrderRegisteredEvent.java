package ru.ufanet.coffeeshoporderingsystem.event.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.ufanet.coffeeshoporderingsystem.api.rest.exchange.request.ClientInfoRequest;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderRegisteredEvent extends AbstractOrderEvent {

    private final String eventName = "OrderRegisteredEvent";

    @JsonProperty("client")
    private final ClientInfoRequest client;

    @JsonProperty("employeeId")
    private final UUID employeeId;

    @JsonProperty("productIds")
    private final List<UUID> productIds;

    @JsonProperty("estimatedIssueTime")
    private final Instant estimatedIssueTime;

    @Override
    public UUID getOrderId() {
        return null;
    }

}
