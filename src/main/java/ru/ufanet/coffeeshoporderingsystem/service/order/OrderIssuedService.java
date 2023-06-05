package ru.ufanet.coffeeshoporderingsystem.service.order;

import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.ufanet.coffeeshoporderingsystem.event.order.AbstractOrderEvent;
import ru.ufanet.coffeeshoporderingsystem.event.order.OrderIssuedEvent;
import ru.ufanet.coffeeshoporderingsystem.mapper.OrderMapper;
import ru.ufanet.coffeeshoporderingsystem.persistence.entity.OrderEntity;
import ru.ufanet.coffeeshoporderingsystem.persistence.entity.OrderStatus;
import ru.ufanet.coffeeshoporderingsystem.persistence.repository.OrderRepository;

@Service
public class OrderIssuedService extends AbstractOrderService<OrderIssuedEvent> {

    @Getter
    private final OrderStatus orderStatus = OrderStatus.ISSUED;

    public OrderIssuedService(OrderMapper orderMapper, OrderRepository orderRepository) {
        super(orderMapper, orderRepository);
    }

    @Override
    public Class<OrderIssuedEvent> getType() {
        return OrderIssuedEvent.class;
    }

    @Override
    public void publishEvent(AbstractOrderEvent event) {
        OrderEntity copied = orderMapper.copy(getOrder((OrderIssuedEvent) event));
        copied.setOrderStatus(getOrderStatus());

        orderRepository.save(copied);
    }

    @Override
    public boolean isTransitionValid(OrderEntity order) {
        return order.getOrderStatus() == OrderStatus.READY_FOR_PICKUP;
    }

}
