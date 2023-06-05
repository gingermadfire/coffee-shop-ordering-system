package ru.ufanet.coffeeshoporderingsystem.api.rest.exchange.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse {

    private UUID id;

    private String firstName;

    private String lastName;
}
