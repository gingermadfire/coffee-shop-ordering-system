package ru.ufanet.coffeeshoporderingsystem.service.order;

import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.ufanet.coffeeshoporderingsystem.event.order.AbstractOrderEvent;
import ru.ufanet.coffeeshoporderingsystem.event.order.OrderReadyForPickupEvent;
import ru.ufanet.coffeeshoporderingsystem.mapper.OrderMapper;
import ru.ufanet.coffeeshoporderingsystem.persistence.entity.OrderEntity;
import ru.ufanet.coffeeshoporderingsystem.persistence.entity.OrderStatus;
import ru.ufanet.coffeeshoporderingsystem.persistence.repository.OrderRepository;

@Service
public class OrderReadyForPickupService extends AbstractOrderService<OrderReadyForPickupEvent> {

    public OrderReadyForPickupService(OrderMapper orderMapper, OrderRepository orderRepository) {
        super(orderMapper, orderRepository);
    }

    @Getter
    private final OrderStatus orderStatus = OrderStatus.READY_FOR_PICKUP;

    @Override
    public Class<OrderReadyForPickupEvent> getType() {
        return OrderReadyForPickupEvent.class;
    }

    @Override
    public void publishEvent(AbstractOrderEvent event) {
        OrderEntity copied = orderMapper.copy(getOrder((OrderReadyForPickupEvent) event));
        copied.setOrderStatus(getOrderStatus());

        orderRepository.save(copied);
    }

    @Override
    protected boolean isTransitionValid(OrderEntity order) {
        return order.getOrderStatus() == OrderStatus.PROCESSING;
    }
}
