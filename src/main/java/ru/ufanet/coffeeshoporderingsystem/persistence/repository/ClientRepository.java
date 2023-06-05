package ru.ufanet.coffeeshoporderingsystem.persistence.repository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ufanet.coffeeshoporderingsystem.persistence.entity.ClientEntity;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<ClientEntity, UUID> {

    @Override
    default Optional<ClientEntity> findById(UUID id) {
        return findFirstByIdOrderByDateTimeDesc(id);
    }

    Optional<ClientEntity> findFirstByIdOrderByDateTimeDesc(UUID id);

    @Override
    default ClientEntity getById(UUID id) {
        return findFirstByIdOrderByDateTimeDesc(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Client by id: %s not found", id)));
    }
}
