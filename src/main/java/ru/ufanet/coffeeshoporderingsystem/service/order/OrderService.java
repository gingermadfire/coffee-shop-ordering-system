package ru.ufanet.coffeeshoporderingsystem.service.order;

import org.springframework.transaction.annotation.Transactional;
import ru.ufanet.coffeeshoporderingsystem.api.rest.exchange.response.OrderResponse;
import ru.ufanet.coffeeshoporderingsystem.event.order.AbstractOrderEvent;
import ru.ufanet.coffeeshoporderingsystem.service.EventService;

import java.util.UUID;

public interface OrderService<T extends AbstractOrderEvent> extends EventService {

    Class<T> getType();

    @Transactional(readOnly = true)
    OrderResponse findOrder(UUID id);

    @Transactional
    void publishEvent(AbstractOrderEvent event);

}
