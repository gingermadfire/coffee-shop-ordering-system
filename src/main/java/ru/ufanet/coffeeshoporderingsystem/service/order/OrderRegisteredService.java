package ru.ufanet.coffeeshoporderingsystem.service.order;

import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.ufanet.coffeeshoporderingsystem.event.order.AbstractOrderEvent;
import ru.ufanet.coffeeshoporderingsystem.event.order.OrderRegisteredEvent;
import ru.ufanet.coffeeshoporderingsystem.exception.NotFoundException;
import ru.ufanet.coffeeshoporderingsystem.mapper.OrderMapper;
import ru.ufanet.coffeeshoporderingsystem.persistence.entity.*;
import ru.ufanet.coffeeshoporderingsystem.persistence.repository.ClientRepository;
import ru.ufanet.coffeeshoporderingsystem.persistence.repository.EmployeeRepository;
import ru.ufanet.coffeeshoporderingsystem.persistence.repository.OrderRepository;
import ru.ufanet.coffeeshoporderingsystem.persistence.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderRegisteredService extends AbstractOrderService<OrderRegisteredEvent> {

    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final ProductRepository productRepository;

    public OrderRegisteredService(OrderMapper orderMapper,
                                  OrderRepository orderRepository,
                                  ClientRepository clientRepository,
                                  EmployeeRepository employeeRepository,
                                  ProductRepository productRepository) {
        super(orderMapper, orderRepository);
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
        this.productRepository = productRepository;
    }

    @Getter
    private final OrderStatus orderStatus = OrderStatus.REGISTERED;

    @Override
    public Class<OrderRegisteredEvent> getType() {
        return OrderRegisteredEvent.class;
    }

    @Override
    public void publishEvent(AbstractOrderEvent event) {
        OrderRegisteredEvent orderRegisteredEvent = (OrderRegisteredEvent) event;
        List<UUID> productIds = orderRegisteredEvent.getProductIds();
        List<ProductEntity> products = new ArrayList<>(productIds.size());

        for (UUID productId : productIds) {
            ProductEntity product = productRepository.findById(productId)
                    .orElseThrow(() -> new NotFoundException("Product not found by id: " + productId));
            products.add(product);
        }

        ClientEntity client = clientRepository.findById(orderRegisteredEvent.getClient().getId())
                .orElseGet(() -> clientRepository.save(new ClientEntity(
                        UUID.randomUUID(),
                        orderRegisteredEvent.getClient().getFirstName(),
                        orderRegisteredEvent.getClient().getLastName()
                )));
        EmployeeEntity employee = employeeRepository.findById(event.getEmployeeId())
                .orElseThrow(() -> new NotFoundException("Employee not found"));

        OrderEntity order = new OrderEntity()
                .setId(UUID.randomUUID())
                .setClientId(client.getId())
                .setEmployeeId(employee.getId())
                .setProductIds(productIds)
                .setEstimatedIssueTime(orderRegisteredEvent.getEstimatedIssueTime())
                .setTotalPrice(products.stream().map(ProductEntity::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add))
                .setOrderStatus(getOrderStatus());

        orderRepository.save(order);
    }

    @Override
    protected boolean isTransitionValid(OrderEntity order) {
        return true;
    }
}
