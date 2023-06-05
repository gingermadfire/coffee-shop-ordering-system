package ru.ufanet.coffeeshoporderingsystem.service.order;

import org.springframework.stereotype.Service;
import ru.ufanet.coffeeshoporderingsystem.event.order.AbstractOrderEvent;
import ru.ufanet.coffeeshoporderingsystem.exception.NotFoundException;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderServiceProvider {

    private final Map<Class<? extends AbstractOrderEvent>, OrderService<?>> orderServices;

    public OrderServiceProvider(List<OrderService<?>> orderServices) {
        this.orderServices = orderServices.stream()
                .collect(Collectors.toMap(OrderService::getType, Function.identity()));
    }

    @SuppressWarnings("unchecked")
    public <T extends AbstractOrderEvent> OrderService<T> get(Class<T> aClass) {
        return (OrderService<T>) this.orderServices.get(aClass);
    }

    @SuppressWarnings("unchecked")
    public <T extends AbstractOrderEvent> OrderService<T> get() {
        return (OrderService<T>) this.orderServices.values().stream()
                .findAny()
                .orElseThrow(() -> new NotFoundException("No OrderService implementation found"));
    }
}
