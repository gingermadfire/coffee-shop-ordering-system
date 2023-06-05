package ru.ufanet.coffeeshoporderingsystem.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ufanet.coffeeshoporderingsystem.persistence.entity.OrderEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {

    @Override
    default Optional<OrderEntity> findById(UUID id) {
        return findFirstByIdOrderByDateTimeDesc(id);
    }

    default List<OrderEntity> findAllById(UUID id) {
        return findAllByIdOrderByDateTimeAsc(id);
    }

    Optional<OrderEntity> findFirstByIdOrderByDateTimeDesc(UUID id);

    List<OrderEntity> findAllByIdOrderByDateTimeAsc(UUID id);

}
