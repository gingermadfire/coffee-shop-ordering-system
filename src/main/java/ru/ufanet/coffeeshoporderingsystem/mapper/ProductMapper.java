package ru.ufanet.coffeeshoporderingsystem.mapper;

import org.springframework.stereotype.Component;
import ru.ufanet.coffeeshoporderingsystem.api.rest.exchange.response.ProductResponse;
import ru.ufanet.coffeeshoporderingsystem.persistence.entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {

    public ProductResponse map(ProductEntity product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice()
        );
    }

    public List<ProductResponse> map(List<ProductEntity> productEntities) {
        List<ProductResponse> productResponses = new ArrayList<>();
        for (ProductEntity product : productEntities) {
            productResponses.add(this.map(product));
        }

        return productResponses;
    }

}
