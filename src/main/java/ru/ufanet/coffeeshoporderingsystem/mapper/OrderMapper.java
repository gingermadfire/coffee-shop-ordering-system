package ru.ufanet.coffeeshoporderingsystem.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ufanet.coffeeshoporderingsystem.api.rest.exchange.response.OrderExtendedResponse;
import ru.ufanet.coffeeshoporderingsystem.api.rest.exchange.response.OrderResponse;
import ru.ufanet.coffeeshoporderingsystem.persistence.entity.OrderEntity;
import ru.ufanet.coffeeshoporderingsystem.persistence.repository.ClientRepository;
import ru.ufanet.coffeeshoporderingsystem.persistence.repository.EmployeeRepository;
import ru.ufanet.coffeeshoporderingsystem.persistence.repository.OrderRepository;
import ru.ufanet.coffeeshoporderingsystem.persistence.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final ClientMapper clientMapper;
    private final EmployeeMapper employeeMapper;
    private final ProductMapper productMapper;

    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderResponse map(OrderEntity order) {
        return new OrderResponse(
                order.getId(),
                clientMapper.map(clientRepository.getById(order.getClientId())),
                employeeMapper.map(employeeRepository.getById(order.getEmployeeId())),
                productMapper.map(productRepository.findAllByIds(order.getProductIds())),
                order.getEstimatedIssueTime(),
                order.getOrderStatus(),
                order.getOrderCancellingReason(),
                order.getTotalPrice()
                );
    }

    public List<OrderResponse> map(List<OrderEntity> orderEntities) {
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (OrderEntity order : orderEntities) {
            orderResponses.add(this.map(order));
        }

        return orderResponses;
    }

    public OrderEntity copy(OrderEntity order) {
        return new OrderEntity(
                order.getId(),
                order.getProductIds(),
                order.getClientId(),
                order.getEmployeeId(),
                order.getEstimatedIssueTime(),
                order.getTotalPrice(),
                order.getOrderStatus(),
                order.getOrderCancellingReason()
        );
    }

    public OrderExtendedResponse extend(OrderEntity order) {
        return new OrderExtendedResponse(
                order.getId(),
                clientMapper.map(clientRepository.getById(order.getClientId())),
                employeeMapper.map(employeeRepository.getById(order.getEmployeeId())),
                productMapper.map(productRepository.findAllByIds(order.getProductIds())),
                order.getEstimatedIssueTime(),
                order.getOrderStatus(),
                order.getOrderCancellingReason(),
                order.getTotalPrice(),
                this.map(orderRepository.findAllById(order.getId()))
        );
    }

}
