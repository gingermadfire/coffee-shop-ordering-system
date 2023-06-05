package ru.ufanet.coffeeshoporderingsystem.mapper;

import org.springframework.stereotype.Component;
import ru.ufanet.coffeeshoporderingsystem.api.rest.exchange.response.ClientResponse;
import ru.ufanet.coffeeshoporderingsystem.persistence.entity.ClientEntity;

@Component
public class ClientMapper {

    public ClientResponse map(ClientEntity client) {
        return new ClientResponse(
                client.getId(),
                client.getFirstname(),
                client.getLastname()
        );
    }

}
