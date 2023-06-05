package ru.ufanet.coffeeshoporderingsystem.service.order;

import lombok.RequiredArgsConstructor;
import ru.ufanet.coffeeshoporderingsystem.api.rest.exchange.response.OrderResponse;
import ru.ufanet.coffeeshoporderingsystem.event.order.AbstractOrderEvent;
import ru.ufanet.coffeeshoporderingsystem.exception.NotFoundException;
import ru.ufanet.coffeeshoporderingsystem.exception.OrderStatusException;
import ru.ufanet.coffeeshoporderingsystem.mapper.OrderMapper;
import ru.ufanet.coffeeshoporderingsystem.persistence.entity.OrderEntity;
import ru.ufanet.coffeeshoporderingsystem.persistence.entity.OrderStatus;
import ru.ufanet.coffeeshoporderingsystem.persistence.repository.OrderRepository;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public abstract class AbstractOrderService<T extends AbstractOrderEvent> implements OrderService<T> {

    protected final OrderMapper orderMapper;

    protected final OrderRepository orderRepository;

    protected abstract OrderStatus getOrderStatus();

    protected abstract boolean isTransitionValid(OrderEntity order);

    protected OrderEntity getOrder(T event) {
        Optional<OrderEntity> order = orderRepository.findById(event.getOrderId());

        if (order.isEmpty()) {
            throw new NotFoundException("Order not found by id: " + event.getOrderId());
        }

        return order.filter(this::isTransitionValid)
                .orElseThrow(() -> new OrderStatusException("Invalid transition: " + order.get().getOrderStatus()
                        + " -> " + getOrderStatus()));
    }

    @Override
    public OrderResponse findOrder(UUID id) {
        OrderEntity order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order not found by id: " + id));

        return orderMapper.map(order);
    }
}
