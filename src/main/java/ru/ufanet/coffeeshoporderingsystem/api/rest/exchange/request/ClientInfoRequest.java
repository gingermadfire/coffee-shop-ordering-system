package ru.ufanet.coffeeshoporderingsystem.api.rest.exchange.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientInfoRequest {

    private UUID id;

    private String firstName;

    private String lastName;

}
