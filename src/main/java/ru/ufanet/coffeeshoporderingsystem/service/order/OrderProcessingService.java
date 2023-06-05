package ru.ufanet.coffeeshoporderingsystem.service.order;

import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.ufanet.coffeeshoporderingsystem.event.order.AbstractOrderEvent;
import ru.ufanet.coffeeshoporderingsystem.event.order.OrderProcessingEvent;
import ru.ufanet.coffeeshoporderingsystem.mapper.OrderMapper;
import ru.ufanet.coffeeshoporderingsystem.persistence.entity.OrderEntity;
import ru.ufanet.coffeeshoporderingsystem.persistence.entity.OrderStatus;
import ru.ufanet.coffeeshoporderingsystem.persistence.repository.OrderRepository;

import java.time.Duration;
import java.time.Instant;

@Service
public class OrderProcessingService extends AbstractOrderService<OrderProcessingEvent> {

    @Getter
    private final OrderStatus orderStatus = OrderStatus.PROCESSING;

    public OrderProcessingService(OrderMapper orderMapper, OrderRepository orderRepository) {
        super(orderMapper, orderRepository);
    }

    @Override
    public Class<OrderProcessingEvent> getType() {
        return OrderProcessingEvent.class;
    }

    @Override
    public void publishEvent(AbstractOrderEvent event) {
        OrderEntity copied = orderMapper.copy(getOrder((OrderProcessingEvent) event));
        copied.setOrderStatus(getOrderStatus());
        long generatedLong = 1L + (long) (Math.random() * (10L - 1L));
        copied.setEstimatedIssueTime(Instant.now().plus(Duration.ofMinutes(generatedLong))); // Random time between 1 and 10 minutes

        orderRepository.save(copied);
    }

    @Override
    public boolean isTransitionValid(OrderEntity order) {
        return order.getOrderStatus() == OrderStatus.REGISTERED;
    }

}
