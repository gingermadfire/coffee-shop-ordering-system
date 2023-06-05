package ru.ufanet.coffeeshoporderingsystem.mapper;

import org.springframework.stereotype.Component;
import ru.ufanet.coffeeshoporderingsystem.api.rest.exchange.response.EmployeeResponse;
import ru.ufanet.coffeeshoporderingsystem.persistence.entity.EmployeeEntity;

@Component
public class EmployeeMapper {

    public EmployeeResponse map(EmployeeEntity employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getFirstname(),
                employee.getLastname()
        );
    }

}
