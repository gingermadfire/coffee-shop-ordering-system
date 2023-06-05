package ru.ufanet.coffeeshoporderingsystem.api.rest.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ufanet.coffeeshoporderingsystem.api.rest.exchange.response.OrderResponse;
import ru.ufanet.coffeeshoporderingsystem.event.order.AbstractOrderEvent;
import ru.ufanet.coffeeshoporderingsystem.service.order.OrderServiceProvider;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderRestController {

    private final OrderServiceProvider orderServiceProvider;

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> findOrder(@PathVariable UUID id) {
        return ResponseEntity.ok(orderServiceProvider.get().findOrder(id));
    }

    @PostMapping
    public ResponseEntity<Void> publishEvent(@RequestBody AbstractOrderEvent event) {
        orderServiceProvider.get(event.getClass()).publishEvent(event);
        return ResponseEntity.ok().build();
    }

}
