package ru.ufanet.coffeeshoporderingsystem.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ufanet.coffeeshoporderingsystem.exception.NotFoundException;
import ru.ufanet.coffeeshoporderingsystem.persistence.entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    @Override
    default Optional<ProductEntity> findById(UUID id) {
        return findFirstByIdOrderByDateTimeDesc(id);
    }

    Optional<ProductEntity> findFirstByIdOrderByDateTimeDesc(UUID id);

    default List<ProductEntity> findAllByIds(List<UUID> ids) {
        List<ProductEntity> products = new ArrayList<>(ids.size());
        for (UUID id : ids) {
            products.add(findById(id)
                    .orElseThrow(() -> new NotFoundException("Product not found")));
        }

        return products;
    }
}
