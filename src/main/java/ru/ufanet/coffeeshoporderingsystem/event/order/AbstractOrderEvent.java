package ru.ufanet.coffeeshoporderingsystem.event.order;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ru.ufanet.coffeeshoporderingsystem.event.Event;

import java.util.UUID;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "eventName", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = OrderCancelledEvent.class, name = "OrderCancelledEvent"),
        @JsonSubTypes.Type(value = OrderIssuedEvent.class, name = "OrderIssuedEvent"),
        @JsonSubTypes.Type(value = OrderProcessingEvent.class, name = "OrderProcessingEvent"),
        @JsonSubTypes.Type(value = OrderReadyForPickupEvent.class, name = "OrderReadyForPickupEvent"),
        @JsonSubTypes.Type(value = OrderRegisteredEvent.class, name = "OrderRegisteredEvent")
})
public abstract class AbstractOrderEvent implements Event {

    public abstract UUID getOrderId();

    public abstract UUID getEmployeeId();

}
