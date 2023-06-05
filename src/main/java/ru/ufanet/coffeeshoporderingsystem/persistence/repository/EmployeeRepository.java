package ru.ufanet.coffeeshoporderingsystem.persistence.repository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ufanet.coffeeshoporderingsystem.persistence.entity.EmployeeEntity;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, UUID> {

    @Override
    default Optional<EmployeeEntity> findById(UUID id) {
        return findFirstByIdOrderByDateTimeDesc(id);
    }

    Optional<EmployeeEntity> findFirstByIdOrderByDateTimeDesc(UUID id);

    @Override
    default EmployeeEntity getById(UUID id) {
        return findFirstByIdOrderByDateTimeDesc(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Employee by id: %s not found", id)));
    }
}
