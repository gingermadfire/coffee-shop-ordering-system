package ru.ufanet.coffeeshoporderingsystem.service.order;

import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.ufanet.coffeeshoporderingsystem.event.order.AbstractOrderEvent;
import ru.ufanet.coffeeshoporderingsystem.event.order.OrderCancelledEvent;
import ru.ufanet.coffeeshoporderingsystem.mapper.OrderMapper;
import ru.ufanet.coffeeshoporderingsystem.persistence.entity.OrderEntity;
import ru.ufanet.coffeeshoporderingsystem.persistence.entity.OrderStatus;
import ru.ufanet.coffeeshoporderingsystem.persistence.repository.OrderRepository;

import java.util.Set;

@Service
public class OrderCancelledService extends AbstractOrderService<OrderCancelledEvent> {

    public OrderCancelledService(OrderMapper orderMapper, OrderRepository orderRepository) {
        super(orderMapper, orderRepository);
    }

    @Getter
    private final OrderStatus orderStatus = OrderStatus.CANCELLED;

    @Override
    public Class<OrderCancelledEvent> getType() {
        return OrderCancelledEvent.class;
    }

    @Override
    public void publishEvent(AbstractOrderEvent event) {
        OrderCancelledEvent orderCancelledEvent = (OrderCancelledEvent) event;
        OrderEntity copied = orderMapper.copy(getOrder(orderCancelledEvent));
        copied.setOrderStatus(getOrderStatus());
        copied.setOrderCancellingReason(orderCancelledEvent.getOrderCancellingReason());

        orderRepository.save(copied);
    }

    @Override
    public boolean isTransitionValid(OrderEntity order) {
        return !Set.of(OrderStatus.ISSUED, OrderStatus.CANCELLED).contains(order.getOrderStatus());
    }
}
